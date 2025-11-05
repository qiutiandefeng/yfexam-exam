package com.yf.exam.modules.cheat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.util.Date;

/**
 * 作弊监控实体类
 * 
 * @author Trae AI
 * @since 2023-10-01
 */
@Data
@TableName("el_cheat_monitor")
public class CheatMonitor extends Model<CheatMonitor> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 考试ID
     */
    @TableField("exam_id")
    private String examId;

    /**
     * 试卷ID
     */
    @TableField("paper_id")
    private String paperId;

    /**
     * 风险值（0-100）
     */
    private Integer riskScore;

    /**
     * 风险等级：0-安全，1-可疑，2-高风险
     */
    private Integer riskLevel;

    /**
     * 行为异常度
     */
    @TableField("behavior_anomaly")
    private Double behaviorAnomaly;

    /**
     * 环境稳定性
     */
    @TableField("env_stability")
    private Double envStability;

    /**
     * 答题特征
     */
    @TableField("answer_feature")
    private Double answerFeature;

    /**
     * 时间匹配度
     */
    @TableField("time_match")
    private Double timeMatch;

    /**
     * 最新监控时间
     */
    @TableField("monitor_time")
    private Date monitorTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}