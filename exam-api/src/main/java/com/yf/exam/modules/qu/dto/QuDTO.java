package com.yf.exam.modules.qu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
* 问题题目请求类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 13:23
*/
@Data
@ApiModel(value="问题题目", description="问题题目")
public class QuDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "题目ID", required=true)
    private String id;

    @ApiModelProperty(value = "题目类型", required=true)
    private Integer quType;

    @ApiModelProperty(value = "1普通,2较难", required=true)
    private Integer level;

    @ApiModelProperty(value = "题目图片", required=true)
    private String image;

    @ApiModelProperty(value = "题目内容", required=true)
    private String content;


    @ApiModelProperty(value = "创建时间", required=true)
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required=true)
    private Date updateTime;

    @ApiModelProperty(value = "题目备注", required=true)
    private String remark;

    @ApiModelProperty(value = "整题解析", required=true)
    private String analysis;
    
}
