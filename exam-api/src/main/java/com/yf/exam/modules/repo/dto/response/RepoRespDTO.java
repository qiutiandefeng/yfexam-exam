package com.yf.exam.modules.repo.dto.response;

import com.yf.exam.modules.repo.dto.RepoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* <p>
* 题库请求类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 13:23
*/
@Data
@ApiModel(value="题库分页响应类", description="题库分页响应类")
public class RepoRespDTO extends RepoDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "多选题数量", required=true)
    private Integer multiCount;

    @ApiModelProperty(value = "单选题数量", required=true)
    private Integer radioCount;

    @ApiModelProperty(value = "判断题数量", required=true)
    private Integer judgeCount;

}
