package com.yf.exam.modules.exam.dto.response;

import com.yf.exam.modules.exam.dto.ExamDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* <p>
* 考试分页响应类
* </p>
*
* @author 聪明笨狗
* @since 2020-07-25 16:18
*/
@Data
@ApiModel(value="阅卷分页响应类", description="阅卷分页响应类")
public class ExamReviewRespDTO extends ExamDTO {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考试人数", required=true)
    private Integer examUser;

    @ApiModelProperty(value = "待阅试卷", required=true)
    private Integer unreadPaper;



}
