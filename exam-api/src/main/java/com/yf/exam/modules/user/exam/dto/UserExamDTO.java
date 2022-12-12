package com.yf.exam.modules.user.exam.dto;

import com.yf.exam.core.annon.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

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
public class UserExamDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    private String id;
    
    @ApiModelProperty(value = "用户ID", required=true)
    private String userId;

    @Dict(dictTable = "el_exam", dicText = "title", dicCode = "id")
    @ApiModelProperty(value = "考试ID", required=true)
    private String examId;
    
    @ApiModelProperty(value = "考试次数", required=true)
    private Integer tryCount;
    
    @ApiModelProperty(value = "最高分数", required=true)
    private Integer maxScore;
    
    @ApiModelProperty(value = "是否通过", required=true)
    private Boolean passed;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    
}
