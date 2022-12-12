package com.yf.exam.modules.sys.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yf.exam.modules.sys.user.dto.SysUserDTO;
import com.yf.exam.modules.sys.user.dto.request.SysUserSaveReqDTO;
import com.yf.exam.modules.sys.user.dto.response.SysUserLoginDTO;
import com.yf.exam.modules.sys.user.entity.SysUser;
import com.yf.exam.core.api.dto.PagingReqDTO;

/**
* <p>
* 管理用户业务类
* </p>
*
* @author 聪明笨狗
* @since 2020-04-13 16:57
*/
public interface SysUserService extends IService<SysUser> {

    /**
    * 分页查询数据
    * @param reqDTO
    * @return
    */
    IPage<SysUserDTO> paging(PagingReqDTO<SysUserDTO> reqDTO);

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    SysUserLoginDTO login(String userName, String password);

    /**
     * 获取管理会话
     * @param token
     * @return
     */
    SysUserLoginDTO token(String token);

    /**
     * 退出登录
     * @param token
     */
    void logout(String token);

    /**
     * 修改用户资料
     * @param reqDTO
     */
    void update(SysUserDTO reqDTO);

    /**
     * 保存添加系统用户
     * @param reqDTO
     */
    void save(SysUserSaveReqDTO reqDTO);

    /**
     * 用户注册
     * @param reqDTO
     */
    SysUserLoginDTO reg(SysUserDTO reqDTO);

    /**
     * 快速注册
     * @param reqDTO
     */
    SysUserLoginDTO quickReg(SysUserDTO reqDTO);
}
