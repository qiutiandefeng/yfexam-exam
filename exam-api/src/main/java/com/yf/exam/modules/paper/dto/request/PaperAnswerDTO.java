package com.yf.exam.modules.paper.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author bool
 */
@Data
@ApiModel(value="查找试卷题目详情请求类", description="查找试卷题目详情请求类")
public class PaperAnswerDTO extends PaperQuQueryDTO {

    @ApiModelProperty(value = "回答列表", required=true)
    private List<String> answers;

    @ApiModelProperty(value = "主观答案", required=true)
    private String answer;

}
