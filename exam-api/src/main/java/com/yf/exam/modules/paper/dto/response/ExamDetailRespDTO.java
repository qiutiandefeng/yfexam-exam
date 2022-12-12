package com.yf.exam.modules.paper.dto.response;

import com.yf.exam.modules.paper.dto.PaperDTO;
import com.yf.exam.modules.paper.dto.PaperQuDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
@ApiModel(value="考试详情", description="考试详情")
public class ExamDetailRespDTO extends PaperDTO {


    @ApiModelProperty(value = "单选题列表", required=true)
    private List<PaperQuDTO> radioList;

    @ApiModelProperty(value = "多选题列表", required=true)
    private List<PaperQuDTO> multiList;

    @ApiModelProperty(value = "判断题", required=true)
    private List<PaperQuDTO> judgeList;


    @ApiModelProperty(value = "剩余结束秒数", required=true)
    public Long getLeftSeconds(){

        // 结束时间
        Calendar cl = Calendar.getInstance();
        cl.setTime(this.getCreateTime());
        cl.add(Calendar.MINUTE, getTotalTime());

        return (cl.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

}
