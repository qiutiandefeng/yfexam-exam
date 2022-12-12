package com.yf.exam.modules.paper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.modules.paper.dto.PaperQuDTO;
import com.yf.exam.modules.paper.dto.ext.PaperQuDetailDTO;
import com.yf.exam.modules.paper.entity.PaperQu;

import java.util.List;

/**
* <p>
* 试卷考题业务类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 16:33
*/
public interface PaperQuService extends IService<PaperQu> {

    /**
    * 分页查询数据
    * @param reqDTO
    * @return
    */
    IPage<PaperQuDTO> paging(PagingReqDTO<PaperQuDTO> reqDTO);

    /**
     * 根据试卷找出题目列表
     * @param paperId
     * @return
     */
    List<PaperQuDTO> listByPaper(String paperId);

    /**
     * 查找详情
     * @param paperId
     * @param quId
     * @return
     */
    PaperQu findByKey(String paperId, String quId);

    /**
     * 根据组合索引更新
     * @param qu
     */
    void updateByKey(PaperQu qu);

    /**
     * 统计客观分
     * @param paperId
     * @return
     */
    int sumObjective(String paperId);

    /**
     * 统计主观分
     * @param paperId
     * @return
     */
    int sumSubjective(String paperId);

    /**
     * 找出全部试题列表
     * @param paperId
     * @return
     */
    List<PaperQuDetailDTO> listForPaperResult(String paperId);
}
