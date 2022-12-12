package com.yf.exam.modules.user;

import com.yf.exam.core.api.ApiError;
import com.yf.exam.core.exception.ServiceException;
import com.yf.exam.modules.sys.user.dto.response.SysUserLoginDTO;
import org.apache.shiro.SecurityUtils;

/**
 * 用户静态工具类
 * @author bool
 */
public class UserUtils {


    /**
     * 获取当前登录用户的ID
     * @param throwable
     * @return
     */
    public static String getUserId(boolean throwable){
        try {
            return ((SysUserLoginDTO) SecurityUtils.getSubject().getPrincipal()).getId();
        }catch (Exception e){
            if(throwable){
                throw new ServiceException(ApiError.ERROR_10010002);
            }
            return null;
        }
    }

    /**
     * 获取当前登录用户的ID
     * @param throwable
     * @return
     */
    public static boolean isAdmin(boolean throwable){
        try {
            SysUserLoginDTO dto = ((SysUserLoginDTO) SecurityUtils.getSubject().getPrincipal());
            return dto.getRoles().contains("sa");
        }catch (Exception e){
            if(throwable){
                throw new ServiceException(ApiError.ERROR_10010002);
            }
        }

        return false;
    }

    /**
     * 获取当前登录用户的ID，默认是会抛异常的
     * @return
     */
    public static String getUserId(){
        return getUserId(true);
    }
}
