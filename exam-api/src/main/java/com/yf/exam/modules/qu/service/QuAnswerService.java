package com.yf.exam.modules.qu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.modules.qu.dto.QuAnswerDTO;
import com.yf.exam.modules.qu.entity.QuAnswer;

import java.util.List;

/**
* <p>
* 候选答案业务类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 13:23
*/
public interface QuAnswerService extends IService<QuAnswer> {

    /**
    * 分页查询数据
    * @param reqDTO
    * @return
    */
    IPage<QuAnswerDTO> paging(PagingReqDTO<QuAnswerDTO> reqDTO);

    /**
     * 根据题目ID查询答案并随机
     * @param quId
     * @return
     */
    List<QuAnswer> listAnswerByRandom(String quId);

    /**
     * 根据问题查找答案
     * @param quId
     * @return
     */
    List<QuAnswerDTO> listByQu(String quId);

    /**
     * 保存试题
     * @param quId
     * @param list
     */
    void saveAll(String quId, List<QuAnswerDTO> list);
}
