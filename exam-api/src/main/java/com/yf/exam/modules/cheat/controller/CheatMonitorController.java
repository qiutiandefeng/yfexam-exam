package com.yf.exam.modules.cheat.controller;

import com.yf.exam.core.api.ApiRest;
import com.yf.exam.core.api.controller.BaseController;
import com.yf.exam.modules.cheat.dto.request.BehaviorDataUploadReqDTO;
import com.yf.exam.modules.cheat.dto.response.RiskScoreRespDTO;
import com.yf.exam.modules.cheat.service.CheatMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作弊监控控制器
 * 
 * @author Trae AI
 * @since 2023-10-01
 */
@Api(tags = {"作弊监控"})
@RestController
@RequestMapping("/exam/api/cheat/monitor")
public class CheatMonitorController extends BaseController {

    @Autowired
    private CheatMonitorService cheatMonitorService;

    /**
     * 上传行为数据
     * 
     * @param reqDTO 行为数据上传请求
     * @return 风险评分响应
     */
    @ApiOperation(value = "上传行为数据")
    @RequestMapping(value = "/upload-behavior", method = {RequestMethod.POST})
    public ApiRest<RiskScoreRespDTO> uploadBehaviorData(@RequestBody BehaviorDataUploadReqDTO reqDTO) {
        RiskScoreRespDTO resp = cheatMonitorService.uploadBehaviorData(reqDTO);
        return super.success(resp);
    }

    /**
     * 获取风险评分
     * 
     * @param userId 用户ID
     * @param examId 考试ID
     * @param paperId 试卷ID
     * @return 风险评分响应
     */
    @ApiOperation(value = "获取风险评分")
    @RequestMapping(value = "/risk-score", method = {RequestMethod.GET})
    public ApiRest<RiskScoreRespDTO> getRiskScore(String userId, String examId, String paperId) {
        RiskScoreRespDTO resp = cheatMonitorService.calculateRiskScore(userId, examId, paperId);
        return super.success(resp);
    }
}