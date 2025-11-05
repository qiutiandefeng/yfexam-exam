package com.yf.exam.modules.cheat.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 风险评分响应DTO
 * 
 * @author Trae AI
 * @since 2023-10-01
 */
@Data
@ApiModel(value="风险评分响应", description="风险评分响应")
public class RiskScoreRespDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "风险值（0-100）", required = true)
    private Integer riskScore;

    @ApiModelProperty(value = "风险等级：0-安全，1-可疑，2-高风险", required = true)
    private Integer riskLevel;

    @ApiModelProperty(value = "风险描述", required = true)
    private String riskDesc;

    @ApiModelProperty(value = "是否需要调整试卷", required = true)
    private Boolean needAdjustPaper;
}