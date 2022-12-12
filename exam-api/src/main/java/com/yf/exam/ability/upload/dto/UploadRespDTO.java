package com.yf.exam.ability.upload.dto;

import com.yf.exam.core.api.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 上传文件结果
 * @author bool
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="文件上传响应", description="文件上传响应")
public class UploadRespDTO extends BaseDTO {

    @ApiModelProperty(value = "上传后的完整的URL地址", required=true)
    private String url;

}
