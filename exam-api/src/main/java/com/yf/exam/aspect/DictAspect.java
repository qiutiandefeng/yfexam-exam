package com.yf.exam.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yf.exam.core.annon.Dict;
import com.yf.exam.core.api.ApiRest;
import com.yf.exam.core.utils.Reflections;
import com.yf.exam.modules.sys.system.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据字典AOP类，处理数据字典值
 *
 * @author bool
 */
@Aspect
@Component
@Slf4j
public class DictAspect {

    @Autowired
    private SysDictService sysDictService;

    /**
     * 切入Controller执行
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(public *  com.yf.exam..*.*Controller.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        return this.translate(pjp);
    }

    /**
     * 进行翻译并返回，调用前必须实现：BaseDictService
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    public Object translate(ProceedingJoinPoint pjp) throws Throwable {
        // 处理字典
        return this.parseAllDictText(pjp.proceed());
    }

    /**
     * 转换全部数据字典
     *
     * @param result
     */
    private Object parseAllDictText(Object result) {

        // 非ApiRest类型不处理
        if (result instanceof ApiRest) {
            parseFullDictText(result);
        }

        return result;
    }


    /**
     * 转换所有类型的数据字典、包含子列表
     *
     * @param result
     */
    private void parseFullDictText(Object result) {

        try {

            Object rest = ((ApiRest) result).getData();

            // 不处理普通数据类型
            if (rest == null || this.isBaseType(rest.getClass())) {
                return;
            }

            // 分页的
            if (rest instanceof IPage) {
                List<Object> items = new ArrayList<>(16);
                for (Object record : ((IPage) rest).getRecords()) {
                    Object item = this.parseObject(record);
                    items.add(item);
                }
                ((IPage) rest).setRecords(items);
                return;
            }

            // 数据列表的
            if (rest instanceof List) {
                List<Object> items = new ArrayList<>();
                for (Object record : ((List) rest)) {
                    Object item = this.parseObject(record);
                    items.add(item);
                }
                // 重新回写值
                ((ApiRest) result).setData(items);
                return;
            }

            // 处理单对象
            Object item = this.parseObject(((ApiRest) result).getData());
            ((ApiRest) result).setData(item);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理数据字典值
     *
     * @param record
     * @return
     */
    public Object parseObject(Object record) {

        if (record == null) {
            return null;
        }

        // 不处理普通数据类型
        if (this.isBaseType(record.getClass())) {
            return record;
        }

        // 转换JSON字符
        String json = JSON.toJSONString(record);
        JSONObject item = JSONObject.parseObject(json);

        for (Field field : Reflections.getAllFields(record)) {

            // 如果是List类型
            if (List.class.isAssignableFrom(field.getType())) {
                try {
                    List list = this.processList(field, item.getObject(field.getName(), List.class));
                    item.put(field.getName(), list);
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }

            // 处理普通字段
            if (field.getAnnotation(Dict.class) != null) {
                String code = field.getAnnotation(Dict.class).dicCode();
                String text = field.getAnnotation(Dict.class).dicText();
                String table = field.getAnnotation(Dict.class).dictTable();
                String key = String.valueOf(item.get(field.getName()));

                //翻译字典值对应的txt
                String textValue = this.translateDictValue(code, text, table, key);
                if (StringUtils.isEmpty(textValue)) {
                    textValue = "";
                }
                item.put(field.getName() + "_dictText", textValue);
                continue;
            }

            //日期格式转换
            if ("java.util.Date".equals(field.getType().getName()) && item.get(field.getName()) != null) {

                // 获取注解
                JsonFormat ann = field.getAnnotation(JsonFormat.class);
                // 格式化方式
                SimpleDateFormat fmt;

                // 使用注解指定的
                if (ann != null && !StringUtils.isEmpty(ann.pattern())) {
                    fmt = new SimpleDateFormat(ann.pattern());
                } else {
                    // 默认时间样式
                    fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                item.put(field.getName(), fmt.format(new Date((Long) item.get(field.getName()))));
                continue;

            }
        }

        return item;
    }

    /**
     * 获得类型为List的值
     *
     * @param field
     * @return
     */
    private List<Object> processList(Field field, List list) {

        // 空判断
        if (list == null || list.size() == 0) {
            return new ArrayList<>();
        }

        // 获得List属性的真实类
        Type genericType = field.getGenericType();
        Class<?> actualType = null;
        if (genericType instanceof ParameterizedType) {
            // 尝试获取数据类型
            ParameterizedType pt = (ParameterizedType) genericType;
            try {
                actualType = (Class) pt.getActualTypeArguments()[0];
            }catch (Exception e){
                return list;
            }
        }

        // 常规列表无需处理
        if (isBaseType(actualType)) {
            return list;
        }

        // 返回列表
        List<Object> result = new ArrayList<>(16);

        for (int i = 0; i < list.size(); i++) {
            // 创建实例-->赋值-->字典处理
            Object data = list.get(i);
            try {
                data = JSON.parseObject(JSON.toJSONString(data), actualType);
            }catch (Exception e){
                // 转换出错不处理
            }

            // 处理后的数据
            Object pds = this.parseObject(data);
            result.add(pds);
        }

        return result;
    }

    /**
     * 翻译实现
     *
     * @param code
     * @param text
     * @param table
     * @param key
     * @return
     */
    private String translateDictValue(String code, String text, String table, String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        try {
            // 翻译值
            String dictText = null;
            if (!StringUtils.isEmpty(table)) {
                dictText  = sysDictService.findDict(table, text, code, key.trim());
            }

            if (!StringUtils.isEmpty(dictText)) {
                return dictText;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断是否基本类型
     *
     * @param clazz
     * @return
     */
    private boolean isBaseType(Class clazz) {


        // 基础数据类型
        if (clazz.equals(java.lang.Integer.class) ||
                clazz.equals(java.lang.Byte.class) ||
                clazz.equals(java.lang.Long.class) ||
                clazz.equals(java.lang.Double.class) ||
                clazz.equals(java.lang.Float.class) ||
                clazz.equals(java.lang.Character.class) ||
                clazz.equals(java.lang.Short.class) ||
                clazz.equals(java.lang.Boolean.class)) {
            return true;
        }

        // String类型
        if (clazz.equals(java.lang.String.class)) {
            return true;
        }

        // 数字
        if (clazz.equals(java.lang.Number.class)) {
            return true;
        }

        return false;
    }


}
