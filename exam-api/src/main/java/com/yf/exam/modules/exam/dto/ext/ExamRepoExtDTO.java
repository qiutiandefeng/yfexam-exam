package com.yf.exam.modules.exam.dto.ext;

import com.yf.exam.modules.exam.dto.ExamRepoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* <p>
* 考试题库数据传输类
* </p>
*
* @author 聪明笨狗
* @since 2020-09-05 11:14
*/
@Data
@ApiModel(value="考试题库扩展响应类", description="考试题库扩展响应类")
public class ExamRepoExtDTO extends ExamRepoDTO {

    private static final long serialVersionUID = 1L;

    
    @ApiModelProperty(value = "单选题总量", required=true)
    private Integer totalRadio;
    
    @ApiModelProperty(value = "多选题总量", required=true)
    private Integer totalMulti;
    
    @ApiModelProperty(value = "判断题总量", required=true)
    private Integer totalJudge;
    
}
