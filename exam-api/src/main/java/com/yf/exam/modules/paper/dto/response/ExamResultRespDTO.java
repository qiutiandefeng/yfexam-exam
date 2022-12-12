package com.yf.exam.modules.paper.dto.response;

import com.yf.exam.modules.paper.dto.PaperDTO;
import com.yf.exam.modules.paper.dto.ext.PaperQuDetailDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="考试结果展示响应类", description="考试结果展示响应类")
public class ExamResultRespDTO extends PaperDTO {

    @ApiModelProperty(value = "问题列表", required=true)
    private List<PaperQuDetailDTO> quList;

}
