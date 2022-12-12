package com.yf.exam.modules.user.book.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.modules.qu.entity.Qu;
import com.yf.exam.modules.qu.service.QuService;
import com.yf.exam.modules.user.UserUtils;
import com.yf.exam.modules.user.book.dto.UserBookDTO;
import com.yf.exam.modules.user.book.entity.UserBook;
import com.yf.exam.modules.user.book.mapper.UserBookMapper;
import com.yf.exam.modules.user.book.service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* <p>
* 语言设置 服务实现类
* </p>
*
* @author 聪明笨狗
* @since 2020-05-27 17:56
*/
@Service
public class UserBookServiceImpl extends ServiceImpl<UserBookMapper, UserBook> implements UserBookService {

    @Autowired
    private QuService quService;

    @Override
    public IPage<UserBookDTO> paging(PagingReqDTO<UserBookDTO> reqDTO) {

        //创建分页对象
        Page query = new Page<>(reqDTO.getCurrent(), reqDTO.getSize());

        //查询条件
        QueryWrapper<UserBook> wrapper = new QueryWrapper<>();
        // 查找用户的错题
        wrapper.lambda().eq(UserBook::getUserId, UserUtils.getUserId(true));

        UserBookDTO params = reqDTO.getParams();
        if(params!=null){
            if(!StringUtils.isEmpty(params.getTitle())){
                wrapper.lambda().like(UserBook::getTitle, params.getTitle());
            }

            if(!StringUtils.isEmpty(params.getExamId())){
                wrapper.lambda().eq(UserBook::getExamId, params.getExamId());
            }
        }

        //获得数据
        IPage<UserBook> page = this.page(query, wrapper);
        //转换结果
        IPage<UserBookDTO> pageData = JSON.parseObject(JSON.toJSONString(page), new TypeReference<Page<UserBookDTO>>(){});
        return pageData;
     }



    @Override
    public void addBook(String examId, String quId) {

        QueryWrapper<UserBook> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(UserBook::getUserId, UserUtils.getUserId())
                .eq(UserBook::getExamId, examId)
                .eq(UserBook::getQuId, quId);

        //查找已有的错题信息
        UserBook book = this.getOne(wrapper, false);


        // 问题
        Qu qu = quService.getById(quId);

        if (book == null) {
            book = new UserBook();
            book.setExamId(examId);
            book.setUserId(UserUtils.getUserId());
            book.setTitle(qu.getContent());
            book.setQuId(quId);
            book.setWrongCount(1);
            Integer maxSort = this.findMaxSort(examId, UserUtils.getUserId());
            book.setSort(maxSort+1);

            this.save(book);
        } else {
            book.setWrongCount(book.getWrongCount()+1);
            this.updateById(book);
        }
    }

    @Override
    public String findNext(String examId, String quId) {


        Integer sort = 999999;

        if(!StringUtils.isEmpty(quId)){
            QueryWrapper<UserBook> wrapper = new QueryWrapper<>();
            wrapper.lambda()
                    .eq(UserBook::getUserId, UserUtils.getUserId())
                    .eq(UserBook::getExamId, examId)
                    .eq(UserBook::getQuId, quId);
            wrapper.last(" ORDER BY `sort` DESC");

            UserBook last = this.getOne(wrapper, false);
            if(last!=null){
                sort = last.getSort();
            }
        }

        QueryWrapper<UserBook> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(UserBook::getUserId, UserUtils.getUserId())
                .eq(UserBook::getExamId, examId)
                .lt(UserBook::getSort, sort);
        wrapper.last(" ORDER BY `sort` DESC");

        UserBook next = this.getOne(wrapper, false);
        if(next != null){
            return next.getQuId();
        }

        return null;
    }

    /**
     * 查找最大的排序
     * @param userId
     * @return
     */
    private Integer findMaxSort(String examId, String userId){

        QueryWrapper<UserBook> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(UserBook::getExamId, examId)
                .eq(UserBook::getUserId, userId);
        wrapper.last(" ORDER BY `sort` DESC");

        UserBook book = this.getOne(wrapper, false);
        if(book == null){
            return 0;
        }
        return book.getSort();
    }


}
