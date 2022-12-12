package com.yf.exam.modules.paper.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.core.utils.BeanMapper;
import com.yf.exam.modules.paper.dto.PaperQuDTO;
import com.yf.exam.modules.paper.dto.ext.PaperQuDetailDTO;
import com.yf.exam.modules.paper.entity.PaperQu;
import com.yf.exam.modules.paper.mapper.PaperQuMapper;
import com.yf.exam.modules.paper.service.PaperQuService;
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
public class PaperQuServiceImpl extends ServiceImpl<PaperQuMapper, PaperQu> implements PaperQuService {

    @Override
    public IPage<PaperQuDTO> paging(PagingReqDTO<PaperQuDTO> reqDTO) {

        //创建分页对象
        IPage<PaperQu> query = new Page<>(reqDTO.getCurrent(), reqDTO.getSize());

        //查询条件
        QueryWrapper<PaperQu> wrapper = new QueryWrapper<>();

        //获得数据
        IPage<PaperQu> page = this.page(query, wrapper);
        //转换结果
        IPage<PaperQuDTO> pageData = JSON.parseObject(JSON.toJSONString(page), new TypeReference<Page<PaperQuDTO>>(){});
        return pageData;
     }

    @Override
    public List<PaperQuDTO> listByPaper(String paperId) {

        //查询条件
        QueryWrapper<PaperQu> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PaperQu::getPaperId, paperId)
                .orderByAsc(PaperQu::getSort);

        List<PaperQu> list = this.list(wrapper);
        return BeanMapper.mapList(list, PaperQuDTO.class);
    }

    @Override
    public PaperQu findByKey(String paperId, String quId) {
        //查询条件
        QueryWrapper<PaperQu> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PaperQu::getPaperId, paperId)
                .eq(PaperQu::getQuId, quId);

        return this.getOne(wrapper, false);
    }

    @Override
    public void updateByKey(PaperQu qu) {

        //查询条件
        QueryWrapper<PaperQu> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PaperQu::getPaperId, qu.getPaperId())
                .eq(PaperQu::getQuId, qu.getQuId());

        this.update(qu, wrapper);
    }

    @Override
    public int sumObjective(String paperId) {
        return baseMapper.sumObjective(paperId);
    }

    @Override
    public int sumSubjective(String paperId) {
        return baseMapper.sumSubjective(paperId);
    }

    @Override
    public List<PaperQuDetailDTO> listForPaperResult(String paperId) {
        return baseMapper.listByPaper(paperId);
    }
}
