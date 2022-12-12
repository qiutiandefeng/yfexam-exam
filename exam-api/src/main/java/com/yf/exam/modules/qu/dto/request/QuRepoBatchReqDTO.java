package com.yf.exam.modules.qu.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
* <p>
* 问题题目请求类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 13:23
*/
@Data
@ApiModel(value="试题题库批量操作类", description="试题题库批量操作类")
public class QuRepoBatchReqDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "题目ID", required=true)
    private List<String> quIds;

    @ApiModelProperty(value = "题目类型", required=true)
    private List<String> repoIds;

    @ApiModelProperty(value = "是否移除，否就新增；是就移除", required=true)
    private Boolean remove;
    
}
