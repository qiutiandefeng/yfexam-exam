package com.yf.exam.modules.user.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yf.exam.modules.user.exam.dto.request.UserExamReqDTO;
import com.yf.exam.modules.user.exam.dto.response.UserExamRespDTO;
import com.yf.exam.modules.user.exam.entity.UserExam;
import org.apache.ibatis.annotations.Param;

/**
* <p>
* 考试记录Mapper
* </p>
*
* @author 聪明笨狗
* @since 2020-09-21 15:13
*/
public interface UserExamMapper extends BaseMapper<UserExam> {

    /**
     * 我的考试分页
     * @param page
     * @param query
     * @return
     */
    IPage<UserExamRespDTO> paging(Page page, @Param("query") UserExamReqDTO query);

}
