package com.yf.exam.modules.qu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* <p>
* 试题题库请求类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 13:23
*/
@Data
@ApiModel(value="试题题库", description="试题题库")
public class QuRepoDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;

    @ApiModelProperty(value = "试题", required=true)
    private String quId;

    @ApiModelProperty(value = "归属题库", required=true)
    private String repoId;

    @ApiModelProperty(value = "题目类型", required=true)
    private Integer quType;

    @ApiModelProperty(value = "排序", required=true)
    private Integer sort;

}