package com.yf.exam.modules.repo.dto.request;

import com.yf.exam.modules.repo.dto.RepoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* <p>
* 题库请求类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 13:23
*/
@Data
@ApiModel(value="题库分页请求类", description="题库分页请求类")
public class RepoReqDTO extends RepoDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "排除题库ID", required=true)
    private List<String> excludes;

    @ApiModelProperty(value = "单选题数量", required=true)
    private String title;

}
