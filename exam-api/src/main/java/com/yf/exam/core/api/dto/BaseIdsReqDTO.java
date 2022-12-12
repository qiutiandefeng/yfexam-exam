package com.yf.exam.core.api.dto;

import com.yf.exam.core.api.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 通用ID列表类操作，用于批量删除、修改状态等
 * @author bool 
 * @date 2019-08-01 19:07
 */
@Data
@ApiModel(value="删除参数", description="删除参数")
public class BaseIdsReqDTO extends BaseDTO {


    @JsonIgnore
    private String userId;

    @ApiModelProperty(value = "要删除的ID列表", required = true)
    private List<String> ids;
}
