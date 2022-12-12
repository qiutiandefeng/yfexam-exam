package com.yf.exam.modules.user.book.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
* 错题本请求类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-27 17:56
*/
@Data
@ApiModel(value="错题本", description="错题本")
public class UserBookDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "ID", required=true)
    private String id;

    @ApiModelProperty(value = "考试ID", required=true)
    private String examId;

    @ApiModelProperty(value = "用户ID", required=true)
    private String userId;

    @ApiModelProperty(value = "题目ID", required=true)
    private String quId;

    @ApiModelProperty(value = "加入时间", required=true)
    private Date createTime;

    @ApiModelProperty(value = "最近错误时间", required=true)
    private Date updateTime;

    @ApiModelProperty(value = "错误时间", required=true)
    private Integer wrongCount;

    @ApiModelProperty(value = "题目标题", required=true)
    private String title;

    @ApiModelProperty(value = "错题序号", required=true)
    private Integer sort;

}