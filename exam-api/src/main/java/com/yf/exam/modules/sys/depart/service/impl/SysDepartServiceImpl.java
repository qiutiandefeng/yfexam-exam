package com.yf.exam.modules.sys.depart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.core.utils.BeanMapper;
import com.yf.exam.modules.sys.depart.dto.SysDepartDTO;
import com.yf.exam.modules.sys.depart.dto.response.SysDepartTreeDTO;
import com.yf.exam.modules.sys.depart.entity.SysDepart;
import com.yf.exam.modules.sys.depart.mapper.SysDepartMapper;
import com.yf.exam.modules.sys.depart.service.SysDepartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>
* 部门信息业务实现类
* </p>
*
* @author 聪明笨狗
* @since 2020-09-02 17:25
*/
@Service
public class SysDepartServiceImpl extends ServiceImpl<SysDepartMapper, SysDepart> implements SysDepartService {


    /**
     * 0标识为顶级分类
     */
    private static final String ROOT_TAG = "0";


    @Override
    public void save(SysDepartDTO reqDTO) {

        if(StringUtils.isBlank(reqDTO.getId())) {
            this.fillCode(reqDTO);
        }else{
            reqDTO.setSort(null);
            reqDTO.setDeptCode(null);
        }

        SysDepart entity = new SysDepart();
        BeanMapper.copy(reqDTO, entity);
        this.saveOrUpdate(entity);
    }

    @Override
    public IPage<SysDepartTreeDTO> paging(PagingReqDTO<SysDepartDTO> reqDTO) {

        // 创建分页对象
        Page query = new Page(reqDTO.getCurrent(), reqDTO.getSize());

        // 请求参数
        SysDepartDTO params = reqDTO.getParams();

        //转换结果
        IPage<SysDepartTreeDTO> pageData = baseMapper.paging(query, params);

        return pageData;
     }


    @Override
    public List<SysDepartTreeDTO> findTree() {
        return this.findTree(null);
    }

    @Override
    public List<SysDepartTreeDTO> findTree(List<String> ids) {


        QueryWrapper<SysDepart> wrapper = new QueryWrapper();
        wrapper.lambda().orderByAsc(SysDepart::getSort);

        if(!CollectionUtils.isEmpty(ids)){

            List<String> fullIds = new ArrayList<>();
            for(String id: ids){
                this.cycleAllParent(fullIds, id);
            }

            if(!CollectionUtils.isEmpty(fullIds)){
                wrapper.lambda().in(SysDepart::getId, fullIds);
            }
        }

        //全部列表
        List<SysDepart> list = this.list(wrapper);
        List<SysDepartTreeDTO> dtoList = BeanMapper.mapList(list, SysDepartTreeDTO.class);

        //子结构的列表
        Map<String,List<SysDepartTreeDTO>> map = new HashMap<>(16);

        for(SysDepartTreeDTO item: dtoList){

            //如果存在
            if(map.containsKey(item.getParentId())){
                map.get(item.getParentId()).add(item);
                continue;
            }

            //增加新的结构
            List<SysDepartTreeDTO> a = new ArrayList<>();
            a.add(item);
            map.put(item.getParentId(), a);
        }

        //注意，第0级为顶级的
        List<SysDepartTreeDTO> topList = map.get(ROOT_TAG);
        if(!CollectionUtils.isEmpty(topList)){
            for(SysDepartTreeDTO item: topList){
                this.fillChildren(map, item);
            }
        }

        return topList;
    }

    @Override
    public void sort(String id, Integer sort) {

        SysDepart depart = this.getById(id);
        SysDepart exchange = null;

        QueryWrapper<SysDepart> wrapper = new QueryWrapper<>();
        // 同级排序
        wrapper.lambda()
                .eq(SysDepart::getParentId, depart.getParentId());
        wrapper.last("LIMIT 1");

        // 上升
        if(sort == 0){
            // 同级排序
            wrapper.lambda()
                    .lt(SysDepart::getSort, depart.getSort())
                    .orderByDesc(SysDepart::getSort);
            exchange = this.getOne(wrapper, false);
        }

        // 下降
        if(sort == 1){
            // 同级排序
            wrapper.lambda()
                    .gt(SysDepart::getSort, depart.getSort())
                    .orderByAsc(SysDepart::getSort);
            exchange = this.getOne(wrapper, false);
        }


        if(exchange!=null) {
            SysDepart a = new SysDepart();
            a.setId(id);
            a.setSort(exchange.getSort());
            SysDepart b = new SysDepart();
            b.setId(exchange.getId());
            b.setSort(depart.getSort());
            this.updateById(a);
            this.updateById(b);
        }
    }

    /**
     * 获取部门编号
     * @param reqDTO
     * @return
     */
    private void fillCode(SysDepartDTO reqDTO){

        // 前缀
        String code = "";

        if(StringUtils.isNotBlank(reqDTO.getParentId())
                && !ROOT_TAG.equals(reqDTO.getParentId())){
            SysDepart parent = this.getById(reqDTO.getParentId());
            code = parent.getDeptCode();
        }

        QueryWrapper<SysDepart> wrapper = new QueryWrapper<>();

        // 同级排序
        wrapper.lambda()
                .eq(SysDepart::getParentId, reqDTO.getParentId())
                .orderByDesc(SysDepart::getSort);
        wrapper.last("LIMIT 1");
        SysDepart depart = this.getOne(wrapper, false);

        if(depart !=null){
            code += this.formatCode(depart.getSort()+1);
            reqDTO.setSort(depart.getSort()+1);
        }else{
            code += this.formatCode(1);
            reqDTO.setSort(1);
        }

        reqDTO.setDeptCode(code);
    }

    /**
     * 根式化加0
     * @param sort
     * @return
     */
    private String formatCode(Integer sort){
        if(sort < 10){
            return "A0"+sort;
        }
        return "A"+sort;
    }

    /**
     * 递归去做填充数据
     * @param map
     * @param item
     */
    private void fillChildren(Map<String,List<SysDepartTreeDTO>> map, SysDepartTreeDTO item){

        //设置子类
        if(map.containsKey(item.getId())){

            List<SysDepartTreeDTO> children = map.get(item.getId());
            if(!CollectionUtils.isEmpty(children)){
                for(SysDepartTreeDTO sub: children){
                    this.fillChildren(map, sub);
                }
            }
            item.setChildren(children);
        }
    }


    @Override
    public List<String> listAllSubIds( String id){

        List<String> ids = new ArrayList<>();
        this.cycleAllSubs(ids, id);
        return ids;
    }


    /**
     * 递归所有子级别ID
     * @param list
     * @param id
     */
    private void cycleAllSubs(List<String> list, String id){

        // 添加ID
        list.add(id);

        QueryWrapper<SysDepart> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(SysDepart::getParentId, id)
                .orderByDesc(SysDepart::getSort);
        List<SysDepart> subList = this.list(wrapper);
        if(!CollectionUtils.isEmpty(subList)){
            for(SysDepart item: subList){
                this.cycleAllSubs(list, item.getId());
            }
        }
    }

    /**
     * 递归所有子级别ID
     * @param list
     * @param id
     */
    private void cycleAllParent(List<String> list, String id){

        // 往上递归获得父类
        list.add(id);
        SysDepart depart = this.getById(id);

        if(StringUtils.isNotBlank(depart.getParentId())
                && !ROOT_TAG.equals(depart.getParentId())){
            this.cycleAllParent(list, depart.getParentId());
        }

    }
}
