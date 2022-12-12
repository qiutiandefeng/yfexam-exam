package com.yf.exam.modules.paper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
* <p>
* 试卷考题实体类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 17:31
*/
@Data
@TableName("el_paper_qu")
public class PaperQu extends Model<PaperQu> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 试卷ID
     */
    @TableField("paper_id")
    private String paperId;

    /**
     * 题目ID
     */
    @TableField("qu_id")
    private String quId;

    /**
     * 题目类型
     */
    @TableField("qu_type")
    private Integer quType;

    /**
     * 是否已答
     */
    private Boolean answered;

    /**
     * 主观答案
     */
    private String answer;

    /**
     * 问题排序
     */
    private Integer sort;

    /**
     * 单题分分值
     */
    private Integer score;

    /**
     * 实际得分(主观题)
     */
    @TableField("actual_score")
    private Integer actualScore;

    /**
     * 是否答对
     */
    @TableField("is_right")
    private Boolean isRight;

}
