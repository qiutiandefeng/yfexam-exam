package com.yf.exam.modules.cheat.engine;

import com.yf.exam.modules.cheat.dto.response.RiskScoreRespDTO;
import com.yf.exam.modules.cheat.entity.CheatBehaviorData;
import com.yf.exam.modules.cheat.entity.CheatMonitor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 风险计算引擎
 * 实现实时作弊风险监控的核心算法
 * 
 * @author Trae AI
 * @since 2023-10-01
 */
public class RiskCalculationEngine {
    
    // 风险指标权重配置
    private static final Map<String, Double> RISK_WEIGHTS = Map.of(
        "behaviorAnomaly", 0.3,
        "envStability", 0.25,
        "answerFeature", 0.2,
        "timeMatch", 0.25
        // 可以添加更多指标
    );
    
    // 风险等级阈值
    private static final int SAFE_THRESHOLD = 30;
    private static final int SUSPICIOUS_THRESHOLD = 60;
    
    /**
     * 计算风险评分
     * 
     * @param behaviorDataList 行为数据列表
     * @param monitor 监控记录
     * @return 风险评分响应
     */
    public RiskScoreRespDTO calculateRiskScore(List<CheatBehaviorData> behaviorDataList, CheatMonitor monitor) {
        // 按数据类型分组
        Map<Integer, List<CheatBehaviorData>> dataByType = behaviorDataList.stream()
            .collect(Collectors.groupingBy(CheatBehaviorData::getType));
        
        // 计算各个风险指标
        double behaviorAnomaly = calculateBehaviorAnomaly(dataByType.getOrDefault(1, List.of())); // 鼠标轨迹
        double envStability = calculateEnvStability(dataByType.getOrDefault(4, List.of())); // 设备信息
        double answerFeature = calculateAnswerFeature(dataByType.getOrDefault(2, List.of())); // 键盘输入
        double timeMatch = calculateTimeMatch(dataByType.getOrDefault(3, List.of())); // 窗口状态
        
        // 计算综合风险值
        double totalRisk = behaviorAnomaly * RISK_WEIGHTS.get("behaviorAnomaly") +
                          envStability * RISK_WEIGHTS.get("envStability") +
                          answerFeature * RISK_WEIGHTS.get("answerFeature") +
                          timeMatch * RISK_WEIGHTS.get("timeMatch");
        
        int riskScore = (int) Math.round(totalRisk);
        
        // 确定风险等级
        int riskLevel;
        String riskDesc;
        
        if (riskScore <= SAFE_THRESHOLD) {
            riskLevel = 0;
            riskDesc = "安全";
        } else if (riskScore <= SUSPICIOUS_THRESHOLD) {
            riskLevel = 1;
            riskDesc = "可疑";
        } else {
            riskLevel = 2;
            riskDesc = "高风险";
        }
        
        // 检查是否需要调整试卷
        boolean needAdjustPaper = riskScore > SUSPICIOUS_THRESHOLD;
        
        // 更新监控记录
        updateMonitor(monitor, behaviorAnomaly, envStability, answerFeature, timeMatch, riskScore, riskLevel);
        
        // 构建响应
        RiskScoreRespDTO resp = new RiskScoreRespDTO();
        resp.setRiskScore(riskScore);
        resp.setRiskLevel(riskLevel);
        resp.setRiskDesc(riskDesc);
        resp.setNeedAdjustPaper(needAdjustPaper);
        
        return resp;
    }
    
    /**
     * 计算行为异常度
     * 鼠标轨迹平滑度＜0.3 或加速度标准差＞5 时触发
     * 
     * @param mouseData 鼠标轨迹数据
     * @return 行为异常度（0-100）
     */
    private double calculateBehaviorAnomaly(List<CheatBehaviorData> mouseData) {
        // TODO: 实现鼠标轨迹分析算法
        // 计算轨迹平滑度和加速度标准差
        return 20.0; // 模拟数据
    }
    
    /**
     * 计算环境稳定性
     * IP 切换次数≥2 次/小时或设备指纹匹配度＜80% 时触发
     * 
     * @param deviceData 设备信息数据
     * @return 环境稳定性（0-100）
     */
    private double calculateEnvStability(List<CheatBehaviorData> deviceData) {
        // TODO: 实现设备环境分析算法
        // 检查IP切换和设备指纹
        return 15.0; // 模拟数据
    }
    
    /**
     * 计算答题特征
     * 主观题答案与网络资源相似度＞70% 时触发
     * 
     * @param keyboardData 键盘输入数据
     * @return 答题特征（0-100）
     */
    private double calculateAnswerFeature(List<CheatBehaviorData> keyboardData) {
        // TODO: 实现答题特征分析算法
        // 调用第三方文本比对API
        return 10.0; // 模拟数据
    }
    
    /**
     * 计算时间匹配度
     * 某类题型平均答题时间与用户历史数据偏差＞3 倍标准差时触发
     * 
     * @param windowData 窗口状态数据
     * @return 时间匹配度（0-100）
     */
    private double calculateTimeMatch(List<CheatBehaviorData> windowData) {
        // TODO: 实现时间匹配度分析算法
        // 比较答题时间与历史数据
        return 15.0; // 模拟数据
    }
    
    /**
     * 更新监控记录
     * 
     * @param monitor 监控记录
     * @param behaviorAnomaly 行为异常度
     * @param envStability 环境稳定性
     * @param answerFeature 答题特征
     * @param timeMatch 时间匹配度
     * @param riskScore 风险值
     * @param riskLevel 风险等级
     */
    private void updateMonitor(CheatMonitor monitor, double behaviorAnomaly, double envStability, 
                              double answerFeature, double timeMatch, int riskScore, int riskLevel) {
        monitor.setBehaviorAnomaly(behaviorAnomaly);
        monitor.setEnvStability(envStability);
        monitor.setAnswerFeature(answerFeature);
        monitor.setTimeMatch(timeMatch);
        monitor.setRiskScore(riskScore);
        monitor.setRiskLevel(riskLevel);
    }
}