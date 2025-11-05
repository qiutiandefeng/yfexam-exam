package com.yf.exam.modules.cheat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.util.Date;

/**
 * 作弊记录实体类
 * 
 * @author Trae AI
 * @since 2023-10-01
 */
@Data
@TableName("el_cheat_record")
public class CheatRecord extends Model<CheatRecord> {

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
     * 作弊类型
     */
    private String type;

    /**
     * 作弊描述
     */
    private String description;

    /**
     * 风险值
     */
    private Integer riskScore;

    /**
     * 处理状态：0-未处理，1-已处理
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}