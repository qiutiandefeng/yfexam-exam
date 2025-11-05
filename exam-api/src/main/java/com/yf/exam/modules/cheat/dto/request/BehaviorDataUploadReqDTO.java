package com.yf.exam.modules.cheat.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 行为数据上传请求DTO
 * 
 * @author Trae AI
 * @since 2023-10-01
 */
@Data
@ApiModel(value="行为数据上传请求", description="行为数据上传请求")
public class BehaviorDataUploadReqDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID", required = true)
    private String userId;

    @ApiModelProperty(value = "考试ID", required = true)
    private String examId;

    @ApiModelProperty(value = "试卷ID", required = true)
    private String paperId;

    @ApiModelProperty(value = "行为数据列表", required = true)
    private List<BehaviorDataItem> dataList;

    /**
     * 行为数据项
     */
    @Data
    public static class BehaviorDataItem {
        
        @ApiModelProperty(value = "数据类型：1-鼠标轨迹，2-键盘输入，3-窗口状态，4-设备信息", required = true)
        private Integer type;
        
        @ApiModelProperty(value = "数据内容（JSON格式）", required = true)
        private String content;
        
        @ApiModelProperty(value = "时间戳", required = true)
        private Long timestamp;
    }
}