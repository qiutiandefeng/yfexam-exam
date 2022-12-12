package com.yf.exam.modules.user.exam.dto.request;

import com.yf.exam.modules.user.exam.dto.UserExamDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* <p>
* 考试记录数据传输类
* </p>
*
* @author 聪明笨狗
* @since 2020-09-21 15:13
*/
@Data
@ApiModel(value="考试记录", description="考试记录")
public class UserExamReqDTO extends UserExamDTO {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考试名称", required=true)
    private String title;

    @ApiModelProperty(value = "人员名称", required=true)
    private String realName;


}
