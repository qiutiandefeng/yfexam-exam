package com.yf.exam.modules.cheat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yf.exam.modules.cheat.dto.request.BehaviorDataUploadReqDTO;
import com.yf.exam.modules.cheat.dto.response.RiskScoreRespDTO;
import com.yf.exam.modules.cheat.entity.CheatMonitor;

/**
 * 作弊监控服务接口
 * 
 * @author Trae AI
 * @since 2023-10-01
 */
public interface CheatMonitorService extends IService<CheatMonitor> {

    /**
     * 上传行为数据并计算风险
     * 
     * @param reqDTO 行为数据上传请求
     * @return 风险评分响应
     */
    RiskScoreRespDTO uploadBehaviorData(BehaviorDataUploadReqDTO reqDTO);

    /**
     * 实时计算风险值
     * 
     * @param userId 用户ID
     * @param examId 考试ID
     * @param paperId 试卷ID
     * @return 风险评分响应
     */
    RiskScoreRespDTO calculateRiskScore(String userId, String examId, String paperId);

    /**
     * 获取当前风险状态
     * 
     * @param userId 用户ID
     * @param examId 考试ID
     * @param paperId 试卷ID
     * @return 风险监控信息
     */
    CheatMonitor getCurrentRiskStatus(String userId, String examId, String paperId);

    /**
     * 记录作弊行为
     * 
     * @param userId 用户ID
     * @param examId 考试ID
     * @param paperId 试卷ID
     * @param type 作弊类型
     * @param description 作弊描述
     * @param riskScore 风险值
     */
    void recordCheatBehavior(String userId, String examId, String paperId, String type, String description, Integer riskScore);
}