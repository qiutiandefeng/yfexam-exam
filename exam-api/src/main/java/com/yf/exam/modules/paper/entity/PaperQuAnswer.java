package com.yf.exam.modules.paper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
* <p>
* 试卷考题备选答案实体类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 17:31
*/
@Data
@TableName("el_paper_qu_answer")
public class PaperQuAnswer extends Model<PaperQuAnswer> {


    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 试卷ID
     */
    @TableField("paper_id")
    private String paperId;

    /**
     * 回答项ID
     */
    @TableField("answer_id")
    private String answerId;

    /**
     * 题目ID
     */
    @TableField("qu_id")
    private String quId;

    /**
     * 是否正确项
     */
    @TableField("is_right")
    private Boolean isRight;

    /**
     * 是否选中
     */
    private Boolean checked;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 选项标签
     */
    private String abc;
    
}
