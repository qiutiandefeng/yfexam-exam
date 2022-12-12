package com.yf.exam.modules.paper.dto.ext;

import com.yf.exam.modules.paper.dto.PaperQuAnswerDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* <p>
* 试卷考题备选答案请求类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 17:31
*/
@Data
@ApiModel(value="试卷考题备选答案", description="试卷考题备选答案")
public class PaperQuAnswerExtDTO extends PaperQuAnswerDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试题图片", required=true)
    private String image;

    @ApiModelProperty(value = "答案内容", required=true)
    private String content;

    
}
