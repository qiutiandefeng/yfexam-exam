package com.yf.exam.core.api;


import com.yf.exam.core.api.ApiError;
import com.yf.exam.core.exception.ServiceException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据结果返回的封装
 * @author bool 
 * @date 2018/11/20 09:48
 */
@Data
@NoArgsConstructor
@ApiModel(value="接口响应", description="接口响应")
public class ApiRest<T>{

    /**
     * 响应消息
     */
    @ApiModelProperty(value = "响应消息")
    private String msg;
    /**
     * 响应代码
     */
    @ApiModelProperty(value = "响应代码,0为成功,1为失败", required = true)
    private Integer code;

    /**
     * 请求或响应body
     */
    @ApiModelProperty(value = "响应内容")
    protected T data;


    /**
     * 是否成功
     * @return
     */
    public boolean isSuccess(){
        return code.equals(0);
    }

    /**
     * 构造函数
     * @param error
     */
    public ApiRest(ServiceException error){
        this.code = error.getCode();
        this.msg = error.getMsg();
    }

    /**
     * 构造函数
     * @param error
     */
    public ApiRest(ApiError error){
        this.code = error.getCode();
        this.msg = error.msg;
    }
}
