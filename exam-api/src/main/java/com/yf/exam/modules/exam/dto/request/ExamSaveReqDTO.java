package com.yf.exam.modules.exam.dto.request;

import com.yf.exam.modules.exam.dto.ExamDTO;
import com.yf.exam.modules.exam.dto.ext.ExamRepoExtDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* <p>
* 考试保存请求类
* </p>
*
* @author 聪明笨狗
* @since 2020-07-25 16:18
*/
@Data
@ApiModel(value="考试保存请求类", description="考试保存请求类")
public class ExamSaveReqDTO extends ExamDTO {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "题库列表", required=true)
    private List<ExamRepoExtDTO> repoList;

    @ApiModelProperty(value = "考试部门列表", required=true)
    private List<String> departIds;

}
