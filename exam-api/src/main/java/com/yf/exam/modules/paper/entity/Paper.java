package com.yf.exam.modules.paper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
* <p>
* 试卷实体类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 17:31
*/
@Data
@TableName("el_paper")
public class Paper extends Model<Paper> {

    private static final long serialVersionUID = 1L;

    /**
     * 试卷ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 部门ID
     */
    @TableField("depart_id")
    private String departId;

    /**
     * 规则ID
     */
    @TableField("exam_id")
    private String examId;

    /**
     * 考试标题
     */
    private String title;

    /**
     * 考试时长
     */
    @TableField("total_time")
    private Integer totalTime;

    /**
     * 用户时长
     */
    @TableField("user_time")
    private Integer userTime;

    /**
     * 试卷总分
     */
    @TableField("total_score")
    private Integer totalScore;

    /**
     * 及格分
     */
    @TableField("qualify_score")
    private Integer qualifyScore;

    /**
     * 客观分
     */
    @TableField("obj_score")
    private Integer objScore;

    /**
     * 主观分
     */
    @TableField("subj_score")
    private Integer subjScore;

    /**
     * 用户得分
     */
    @TableField("user_score")
    private Integer userScore;

    /**
     * 是否包含简答题
     */
    @TableField("has_saq")
    private Boolean hasSaq;

    /**
     * 试卷状态
     */
    private Integer state;

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

    /**
     * 截止时间
     */
    @TableField("limit_time")
    private Date limitTime;
}
