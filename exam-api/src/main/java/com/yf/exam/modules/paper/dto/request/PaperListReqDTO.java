package com.yf.exam.modules.paper.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* <p>
* 试卷请求类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 17:31
*/
@Data
@ApiModel(value="试卷", description="试卷")
public class PaperListReqDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID", required=true)
    private String userId;

    @ApiModelProperty(value = "部门ID", required=true)
    private String departId;

    @ApiModelProperty(value = "规则ID", required=true)
    private String examId;

    @ApiModelProperty(value = "用户昵称", required=true)
    private String realName;

    @ApiModelProperty(value = "试卷状态", required=true)
    private Integer state;

    
}
