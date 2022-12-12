package com.yf.exam.modules.paper.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.modules.paper.dto.PaperQuAnswerDTO;
import com.yf.exam.modules.paper.dto.ext.PaperQuAnswerExtDTO;
import com.yf.exam.modules.paper.entity.PaperQuAnswer;
import com.yf.exam.modules.paper.mapper.PaperQuAnswerMapper;
import com.yf.exam.modules.paper.service.PaperQuAnswerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* <p>
* 语言设置 服务实现类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 16:33
*/
@Service
public class PaperQuAnswerServiceImpl extends ServiceImpl<PaperQuAnswerMapper, PaperQuAnswer> implements PaperQuAnswerService {

    @Override
    public IPage<PaperQuAnswerDTO> paging(PagingReqDTO<PaperQuAnswerDTO> reqDTO) {

        //创建分页对象
        IPage<PaperQuAnswer> query = new Page<>(reqDTO.getCurrent(), reqDTO.getSize());

        //查询条件
        QueryWrapper<PaperQuAnswer> wrapper = new QueryWrapper<>();

        //获得数据
        IPage<PaperQuAnswer> page = this.page(query, wrapper);
        //转换结果
        IPage<PaperQuAnswerDTO> pageData = JSON.parseObject(JSON.toJSONString(page), new TypeReference<Page<PaperQuAnswerDTO>>(){});
        return pageData;
     }

    @Override
    public List<PaperQuAnswerExtDTO> listForExam(String paperId, String quId) {
        return baseMapper.list(paperId, quId);
    }

    @Override
    public List<PaperQuAnswer> listForFill(String paperId, String quId) {
        //查询条件
        QueryWrapper<PaperQuAnswer> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(PaperQuAnswer::getPaperId, paperId)
                .eq(PaperQuAnswer::getQuId, quId);

        return this.list(wrapper);
    }
}
