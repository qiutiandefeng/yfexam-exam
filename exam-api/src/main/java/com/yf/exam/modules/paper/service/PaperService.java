package com.yf.exam.modules.paper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.modules.paper.dto.PaperDTO;
import com.yf.exam.modules.paper.dto.ext.PaperQuDetailDTO;
import com.yf.exam.modules.paper.dto.request.PaperAnswerDTO;
import com.yf.exam.modules.paper.dto.request.PaperListReqDTO;
import com.yf.exam.modules.paper.dto.response.ExamDetailRespDTO;
import com.yf.exam.modules.paper.dto.response.ExamResultRespDTO;
import com.yf.exam.modules.paper.dto.response.PaperListRespDTO;
import com.yf.exam.modules.paper.entity.Paper;

/**
* <p>
* 试卷业务类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 16:33
*/
public interface PaperService extends IService<Paper> {

    /**
     * 创建试卷
     * @param userId
     * @param examId
     * @return
     */
    String createPaper(String userId, String examId);


    /**
     * 查找详情
     * @param paperId
     * @return
     */
    ExamDetailRespDTO paperDetail(String paperId);

    /**
     * 考试结果
     * @param paperId
     * @return
     */
    ExamResultRespDTO paperResult(String paperId);

    /**
     * 查找题目详情
     * @param paperId
     * @param quId
     * @return
     */
    PaperQuDetailDTO findQuDetail(String paperId, String quId);

    /**
     * 填充答案
     * @param reqDTO
     */
    void fillAnswer(PaperAnswerDTO reqDTO);

    /**
     * 交卷操作
     * @param paperId
     * @return
     */
    void handExam(String paperId);

    /**
     * 试卷列表响应类
     * @param reqDTO
     * @return
     */
    IPage<PaperListRespDTO> paging(PagingReqDTO<PaperListReqDTO> reqDTO);

    /**
     * 检测是否有进行中的考试
     * @param userId
     * @return
     */
    PaperDTO checkProcess(String userId);

}
