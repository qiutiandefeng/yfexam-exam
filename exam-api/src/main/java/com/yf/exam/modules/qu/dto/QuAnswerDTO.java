package com.yf.exam.modules.qu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* <p>
* 候选答案请求类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 13:23
*/
@Data
@ApiModel(value="候选答案", description="候选答案")
public class QuAnswerDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "答案ID", required=true)
    private String id;

    @ApiModelProperty(value = "问题ID", required=true)
    private String quId;

    @ApiModelProperty(value = "是否正确", required=true)
    private Boolean isRight;

    @ApiModelProperty(value = "选项图片", required=true)
    private String image;

    @ApiModelProperty(value = "答案内容", required=true)
    private String content;

    @ApiModelProperty(value = "答案分析", required=true)
    private String analysis;
    
}
