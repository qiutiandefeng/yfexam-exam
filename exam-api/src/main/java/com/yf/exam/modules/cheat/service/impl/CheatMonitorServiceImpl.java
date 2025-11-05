package com.yf.exam.modules.cheat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yf.exam.modules.cheat.dto.request.BehaviorDataUploadReqDTO;
import com.yf.exam.modules.cheat.dto.response.RiskScoreRespDTO;
import com.yf.exam.modules.cheat.entity.CheatBehaviorData;
import com.yf.exam.modules.cheat.entity.CheatMonitor;
import com.yf.exam.modules.cheat.entity.CheatRecord;
import com.yf.exam.modules.cheat.mapper.CheatBehaviorDataMapper;
import com.yf.exam.modules.cheat.mapper.CheatMonitorMapper;
import com.yf.exam.modules.cheat.mapper.CheatRecordMapper;
import com.yf.exam.modules.cheat.service.CheatMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 作弊监控服务实现
 * 
 * @author Trae AI
 * @since 2023-10-01
 */
@Service
public class CheatMonitorServiceImpl extends ServiceImpl<CheatMonitorMapper, CheatMonitor> implements CheatMonitorService {

    @Autowired
    private CheatBehaviorDataMapper behaviorDataMapper;
    
    @Autowired
    private CheatRecordMapper cheatRecordMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RiskScoreRespDTO uploadBehaviorData(BehaviorDataUploadReqDTO reqDTO) {
        // 保存行为数据
        saveBehaviorData(reqDTO);
        
        // 计算风险值
        return calculateRiskScore(reqDTO.getUserId(), reqDTO.getExamId(), reqDTO.getPaperId());
    }

    @Override
    public RiskScoreRespDTO calculateRiskScore(String userId, String examId, String paperId) {
        // TODO: 实现风险计算逻辑
        // 这里需要根据行为数据计算8个核心指标
        // 行为异常度、环境稳定性、答题特征、时间匹配度等
        
        // 暂时返回模拟数据
        RiskScoreRespDTO resp = new RiskScoreRespDTO();
        resp.setRiskScore(25); // 安全
        resp.setRiskLevel(0);
        resp.setRiskDesc("正常");
        resp.setNeedAdjustPaper(false);
        
        return resp;
    }

    @Override
    public CheatMonitor getCurrentRiskStatus(String userId, String examId, String paperId) {
        QueryWrapper<CheatMonitor> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(CheatMonitor::getUserId, userId)
            .eq(CheatMonitor::getExamId, examId)
            .eq(CheatMonitor::getPaperId, paperId);
        
        return this.getOne(wrapper, false);
    }

    @Override
    public void recordCheatBehavior(String userId, String examId, String paperId, String type, String description, Integer riskScore) {
        CheatRecord record = new CheatRecord();
        record.setUserId(userId);
        record.setExamId(examId);
        record.setPaperId(paperId);
        record.setType(type);
        record.setDescription(description);
        record.setRiskScore(riskScore);
        record.setStatus(0);
        record.setCreateTime(new Date());
        
        cheatRecordMapper.insert(record);
    }
    
    /**
     * 保存行为数据
     */
    private void saveBehaviorData(BehaviorDataUploadReqDTO reqDTO) {
        List<BehaviorDataUploadReqDTO.BehaviorDataItem> dataList = reqDTO.getDataList();
        
        if (dataList != null && !dataList.isEmpty()) {
            Date now = new Date();
            for (BehaviorDataUploadReqDTO.BehaviorDataItem item : dataList) {
                CheatBehaviorData behaviorData = new CheatBehaviorData();
                behaviorData.setUserId(reqDTO.getUserId());
                behaviorData.setExamId(reqDTO.getExamId());
                behaviorData.setPaperId(reqDTO.getPaperId());
                behaviorData.setData(item.getContent());
                behaviorData.setType(item.getType());
                behaviorData.setUploadTime(new Date(item.getTimestamp()));
                behaviorData.setCreateTime(now);
                
                behaviorDataMapper.insert(behaviorData);
            }
        }
    }
}