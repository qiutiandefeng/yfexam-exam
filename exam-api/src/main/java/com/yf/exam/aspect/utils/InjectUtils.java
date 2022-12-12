package com.yf.exam.aspect.utils;

import com.alibaba.fastjson.JSON;
import com.yf.exam.core.api.ApiError;
import com.yf.exam.core.api.ApiRest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 注入工具类
 * @author bool
 * @date 2019-07-17 09:32
 */
@Log4j2
@Component
public class InjectUtils {



    /**
     * 给对象字段赋值
     *
     * @param object 赋值的对象
     * @param value  值
     * @param fields 字段
     * @throws Exception 异常
     */
    public void setValue(Object object, Object value, String... fields) throws Exception {

        //设置同类的属性
        for (String fieldName : fields) {

            //获取当前
            Field field = this.getFiled(object.getClass(), fieldName);
            if(field == null){
                continue;
            }

            field.setAccessible(true);
            field.set(object, value);
        }

    }

    /**
     * 获取字段名对应的字段
     *
     * @param clazz     目标类
     * @param fieldName 字段名
     */
    private Field getFiled(Class clazz, String fieldName) {

        System.out.println("注入的类："+clazz.toString());

        //是否具有包含关系
        try {
            //获取当前类的属性
            return clazz.getDeclaredField(fieldName);
        }catch (Exception e){

            log.error(clazz.toString() + ": not exist field, try superclass " + fieldName);

            //如果为空且存在父类，则往上找
            if(clazz.getSuperclass()!=null){
                return this.getFiled(clazz.getSuperclass(), fieldName);
            }

            return null;
        }
    }


    /**
     * 打印结果返回
     * @param response
     * @throws IOException
     */
    public static void restError(HttpServletResponse response) {

        try {

            //固定错误
            ApiRest apiRest = new ApiRest(ApiError.ERROR_10010002);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(JSON.toJSONString(apiRest));
            response.getWriter().close();

        }catch (IOException e){

        }

    }

}
