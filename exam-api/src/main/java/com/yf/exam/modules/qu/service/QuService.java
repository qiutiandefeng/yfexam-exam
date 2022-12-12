package com.yf.exam.modules.qu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.modules.qu.dto.QuDTO;
import com.yf.exam.modules.qu.dto.export.QuExportDTO;
import com.yf.exam.modules.qu.dto.ext.QuDetailDTO;
import com.yf.exam.modules.qu.dto.request.QuQueryReqDTO;
import com.yf.exam.modules.qu.entity.Qu;

import java.util.List;

/**
* <p>
* 问题题目业务类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 13:23
*/
public interface QuService extends IService<Qu> {

    /**
     * 分页查询数据
     * @param reqDTO
     * @return
     */
    IPage<QuDTO> paging(PagingReqDTO<QuQueryReqDTO> reqDTO);

    /**
     * 删除试题
     * @param ids
     */
    void delete(List<String> ids);

    /**
     * 随机抽取题库的数据
     * @param repoId
     * @param quType
     * @param level  难度等级
     * @param excludes 要排除的ID列表
     * @param size
     * @return
     */
    List<Qu> listByRandom(String repoId,
                          Integer quType,
                          List<String> excludes,
                          Integer size);

    /**
     * 问题详情
     * @param id
     * @return
     */
    QuDetailDTO detail(String id);

    /**
     * 保存试题
     * @param reqDTO
     */
    void save(QuDetailDTO reqDTO);

    /**
     * 查找导出列表
     * @param query
     * @return
     */
    List<QuExportDTO> listForExport(QuQueryReqDTO query);

    /**
     * 导入Excel
     * @param dtoList
     * @return
     */
    int importExcel(List<QuExportDTO> dtoList);
}
