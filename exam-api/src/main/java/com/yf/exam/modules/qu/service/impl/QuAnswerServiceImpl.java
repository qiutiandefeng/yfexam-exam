package com.yf.exam.modules.qu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.core.utils.BeanMapper;
import com.yf.exam.modules.qu.dto.QuAnswerDTO;
import com.yf.exam.modules.qu.entity.QuAnswer;
import com.yf.exam.modules.qu.mapper.QuAnswerMapper;
import com.yf.exam.modules.qu.service.QuAnswerService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
* 语言设置 服务实现类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-25 13:23
*/
@Service
public class QuAnswerServiceImpl extends ServiceImpl<QuAnswerMapper, QuAnswer> implements QuAnswerService {

    @Override
    public IPage<QuAnswerDTO> paging(PagingReqDTO<QuAnswerDTO> reqDTO) {

        //创建分页对象
        IPage<QuAnswer> query = new Page<>(reqDTO.getCurrent(), reqDTO.getSize());

        //查询条件
        QueryWrapper<QuAnswer> wrapper = new QueryWrapper<>();

        //获得数据
        IPage<QuAnswer> page = this.page(query, wrapper);
        //转换结果
        IPage<QuAnswerDTO> pageData = JSON.parseObject(JSON.toJSONString(page), new TypeReference<Page<QuAnswerDTO>>(){});
        return pageData;
     }

    @Override
    public List<QuAnswer> listAnswerByRandom(String quId) {
        QueryWrapper<QuAnswer> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(QuAnswer::getQuId, quId);
        wrapper.last(" ORDER BY RAND() ");

        return this.list(wrapper);
    }

    @Override
    public List<QuAnswerDTO> listByQu(String quId) {
        QueryWrapper<QuAnswer> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(QuAnswer::getQuId, quId);

        List<QuAnswer> list = this.list(wrapper);
        if(!CollectionUtils.isEmpty(list)){
            return BeanMapper.mapList(list, QuAnswerDTO.class);
        }

        return null;
    }


    /**
     * 查找已存在的列表
     * @param quId
     * @return
     */
    public List<String> findExistsList(String quId) {
        //返回结果
        List<String> ids = new ArrayList<>();

        QueryWrapper<QuAnswer> wrapper = new QueryWrapper();
        wrapper.lambda().eq(QuAnswer::getQuId, quId);
        List<QuAnswer> list = this.list(wrapper);

        if (!CollectionUtils.isEmpty(list)) {
            for (QuAnswer item : list) {
                ids.add(item.getId());
            }
        }
        return ids;
    }

    @Override
    public void saveAll(String quId, List<QuAnswerDTO> list) {

        //最终要保存的列表
        List<QuAnswer> saveList = new ArrayList<>();

        //已存在的标签列表
        List<String> ids = this.findExistsList(quId);

        if(!CollectionUtils.isEmpty(list)){
            for(QuAnswerDTO item: list){

                //标签ID
                String id = item.getId();
                QuAnswer answer = new QuAnswer();
                BeanMapper.copy(item, answer);
                answer.setQuId(quId);

                //补全ID避免新增
                if(ids.contains(id)){
                    ids.remove(id);
                }

                saveList.add(answer);
            }

            //保存标签列表
            if(!CollectionUtils.isEmpty(saveList)) {
                this.saveOrUpdateBatch(saveList);
            }

            //删除已移除
            if(!ids.isEmpty()){
                this.removeByIds(ids);
            }
        }else{

            QueryWrapper<QuAnswer> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(QuAnswer::getQuId, quId);
            this.remove(wrapper);
        }
    }


}
