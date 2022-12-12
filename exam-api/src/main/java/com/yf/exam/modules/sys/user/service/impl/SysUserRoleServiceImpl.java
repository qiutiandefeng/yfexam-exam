package com.yf.exam.modules.sys.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yf.exam.modules.sys.user.dto.SysUserRoleDTO;
import com.yf.exam.modules.sys.user.entity.SysUserRole;
import com.yf.exam.modules.sys.user.mapper.SysUserRoleMapper;
import com.yf.exam.modules.sys.user.service.SysUserRoleService;
import com.yf.exam.core.api.dto.PagingReqDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
* 语言设置 服务实现类
* </p>
*
* @author 聪明笨狗
* @since 2020-04-13 16:57
*/
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public IPage<SysUserRoleDTO> paging(PagingReqDTO<SysUserRoleDTO> reqDTO) {

        //创建分页对象
        IPage<SysUserRole> query = new Page<>(reqDTO.getCurrent(), reqDTO.getSize());

        //查询条件
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();

        //获得数据
        IPage<SysUserRole> page = this.page(query, wrapper);
        //转换结果
        IPage<SysUserRoleDTO> pageData = JSON.parseObject(JSON.toJSONString(page), new TypeReference<Page<SysUserRoleDTO>>(){});
        return pageData;
     }

    @Override
    public List<String> listRoles(String userId) {

        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserRole::getUserId, userId);

        List<SysUserRole> list = this.list(wrapper);
        List<String> roles = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            for(SysUserRole item: list){
                roles.add(item.getRoleId());
            }
        }

        return roles;
    }

    @Override
    public String saveRoles(String userId, List<String> ids) {

        // 删除全部角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserRole::getUserId, userId);
        this.remove(wrapper);


        if(!CollectionUtils.isEmpty(ids)){

            List<SysUserRole> list = new ArrayList<>();
            String roleIds = null;

            for(String item: ids){
                SysUserRole role = new SysUserRole();
                role.setRoleId(item);
                role.setUserId(userId);
                list.add(role);
                if(StringUtils.isEmpty(roleIds)){
                    roleIds = item;
                }else{
                    roleIds+=","+item;
                }
            }

            this.saveBatch(list);
            return roleIds;
        }

        return "";
    }

    @Override
    public boolean isStudent(String userId) {

        // 学生角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserRole::getUserId, userId)
                .eq(SysUserRole::getRoleId, "student");

        return this.count(wrapper) > 0;
    }

    @Override
    public boolean isTeacher(String userId) {
        // 学生角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserRole::getUserId, userId)
                .eq(SysUserRole::getRoleId, "teacher");

        return this.count(wrapper) > 0;
    }

    @Override
    public boolean isAdmin(String userId) {
        // 学生角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserRole::getUserId, userId)
                .eq(SysUserRole::getRoleId, "sa");

        return this.count(wrapper) > 0;
    }
}
