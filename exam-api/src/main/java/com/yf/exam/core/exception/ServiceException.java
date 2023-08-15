package com.yf.exam.core.exception;

import com.yf.exam.core.api.ApiError;
import com.yf.exam.core.api.ApiRest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceException extends RuntimeException{

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String msg;

    /**
     * 从结果初始化
     * @param apiRest
     */
    public ServiceException(ApiRest apiRest){
        this.code = apiRest.getCode();
        this.msg = apiRest.getMsg();
    }

    /**
     * 从枚举中获取参数
     * @param apiError
     */
    public ServiceException(ApiError apiError){
        this.code = apiError.getCode();
        this.msg = apiError.msg;
    }

    /**
     * 异常构造
     * @param msg
     */
    public ServiceException(String msg){
        this.code = 1;
        this.msg = msg;
    }

}
