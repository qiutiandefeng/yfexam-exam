package com.yf.exam.modules.cheat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.util.Date;

/**
 * 作弊行为数据实体类
 * 
 * @author Trae AI
 * @since 2023-10-01
 */
@Data
@TableName("el_cheat_behavior_data")
public class CheatBehaviorData extends Model<CheatBehaviorData> {

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
     * 行为数据（JSON格式）
     */
    private String data;

    /**
     * 数据类型：1-鼠标轨迹，2-键盘输入，3-窗口状态，4-设备信息
     */
    private Integer type;

    /**
     * 上传时间
     */
    @TableField("upload_time")
    private Date uploadTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}