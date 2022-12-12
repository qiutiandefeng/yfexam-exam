package com.yf.exam.modules.sys.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* <p>
* 角色请求类
* </p>
*
* @author 聪明笨狗
* @since 2020-04-13 16:57
*/
@Data
@ApiModel(value="角色", description="角色")
public class SysRoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @ApiModelProperty(value = "角色ID", required=true)
    private String id;
    
    @ApiModelProperty(value = "角色名称", required=true)
    private String roleName;
    
}
