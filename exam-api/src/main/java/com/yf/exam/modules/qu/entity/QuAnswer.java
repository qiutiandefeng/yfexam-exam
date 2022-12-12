package com.yf.exam.modules.qu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
* <p>
* 候选答案实体类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 13:23
*/
@Data
@TableName("el_qu_answer")
public class QuAnswer extends Model<QuAnswer> {

    private static final long serialVersionUID = 1L;

    /**
     * 答案ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 问题ID
     */
    @TableField("qu_id")
    private String quId;

    /**
     * 是否正确
     */
    @TableField("is_right")
    private Boolean isRight;

    /**
     * 选项图片
     */
    private String image;

    /**
     * 答案内容
     */
    private String content;


    /**
     * 答案分析
     */
    private String analysis;
    
}
