package com.yf.exam.modules.paper.dto;

import com.yf.exam.core.annon.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
public class PaperDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "试卷ID", required=true)
    private String id;

    @Dict(dictTable = "sys_user", dicText = "real_name", dicCode = "id")
    @ApiModelProperty(value = "用户ID", required=true)
    private String userId;

    @Dict(dictTable = "sys_depart", dicText = "dept_name", dicCode = "id")
    @ApiModelProperty(value = "部门ID", required=true)
    private String departId;

    @ApiModelProperty(value = "规则ID", required=true)
    private String examId;

    @ApiModelProperty(value = "考试标题", required=true)
    private String title;

    @ApiModelProperty(value = "考试时长", required=true)
    private Integer totalTime;

    @ApiModelProperty(value = "用户时长", required=true)
    private Integer userTime;

    @ApiModelProperty(value = "试卷总分", required=true)
    private Integer totalScore;

    @ApiModelProperty(value = "及格分", required=true)
    private Integer qualifyScore;

    @ApiModelProperty(value = "客观分", required=true)
    private Integer objScore;

    @ApiModelProperty(value = "主观分", required=true)
    private Integer subjScore;

    @ApiModelProperty(value = "用户得分", required=true)
    private Integer userScore;

    @ApiModelProperty(value = "是否包含简答题", required=true)
    private Boolean hasSaq;

    @ApiModelProperty(value = "试卷状态", required=true)
    private Integer state;

    @ApiModelProperty(value = "创建时间", required=true)
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required=true)
    private Date updateTime;

    @ApiModelProperty(value = "截止时间")
    private Date limitTime;
    
}
