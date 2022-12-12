package com.yf.exam.modules.sys.depart.dto.response;

import com.yf.exam.modules.sys.depart.dto.SysDepartDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* <p>
* 部门树结构响应类
* </p>
*
* @author 聪明笨狗
* @since 2020-09-02 17:25
*/
@Data
@ApiModel(value="部门树结构响应类", description="部门树结构响应类")
public class SysDepartTreeDTO extends SysDepartDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "子列表", required=true)
    private List<SysDepartTreeDTO> children;

    
}
