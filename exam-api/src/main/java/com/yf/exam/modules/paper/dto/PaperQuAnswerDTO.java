package com.yf.exam.modules.paper.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* <p>
* 试卷考题备选答案请求类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 17:31
*/
@Data
@ApiModel(value="试卷考题备选答案", description="试卷考题备选答案")
public class PaperQuAnswerDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "自增ID", required=true)
    private String id;

    @ApiModelProperty(value = "试卷ID", required=true)
    private String paperId;

    @ApiModelProperty(value = "回答项ID", required=true)
    private String answerId;

    @ApiModelProperty(value = "题目ID", required=true)
    private String quId;

    @ApiModelProperty(value = "是否正确项", required=true)
    private Boolean isRight;

    @ApiModelProperty(value = "是否选中", required=true)
    private Boolean checked;

    @ApiModelProperty(value = "排序", required=true)
    private Integer sort;

    @ApiModelProperty(value = "选项标签", required=true)
    private String abc;
    
}
