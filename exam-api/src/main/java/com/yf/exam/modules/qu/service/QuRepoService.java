package com.yf.exam.modules.qu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.modules.qu.dto.QuRepoDTO;
import com.yf.exam.modules.qu.dto.request.QuRepoBatchReqDTO;
import com.yf.exam.modules.qu.entity.QuRepo;

import java.util.List;

/**
* <p>
* 试题题库业务类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 13:23
*/
public interface QuRepoService extends IService<QuRepo> {

    /**
    * 分页查询数据
    * @param reqDTO
    * @return
    */
    IPage<QuRepoDTO> paging(PagingReqDTO<QuRepoDTO> reqDTO);

    /**
     * 保存全部列表
     * @param quId
     * @param quType
     * @param ids
     */
    void saveAll(String quId, Integer quType, List<String> ids);

    /**
     * 根据问题查找题库
     * @param quId
     * @return
     */
    List<String> listByQu(String quId);

    /**
     * 根据题库查找题目ID列表
     * @param repoId
     * @param quType
     * @param rand
     * @return
     */
    List<String> listByRepo(String repoId, Integer quType, boolean rand);

    /**
     * 批量操作
     * @param reqDTO
     */
    void batchAction(QuRepoBatchReqDTO reqDTO);

}
