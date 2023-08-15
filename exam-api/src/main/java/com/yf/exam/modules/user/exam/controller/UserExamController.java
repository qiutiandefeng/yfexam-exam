package com.yf.exam.modules.user.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yf.exam.core.api.ApiRest;
import com.yf.exam.core.api.controller.BaseController;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.modules.user.exam.dto.request.UserExamReqDTO;
import com.yf.exam.modules.user.exam.dto.response.UserExamRespDTO;
import com.yf.exam.modules.user.exam.service.UserExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 考试记录控制器
* </p>
*
* @author 聪明笨狗
* @since 2020-09-21 15:13
*/
@Api(tags={"考试记录"})
@RestController
@RequestMapping("/exam/api/user/exam")
public class UserExamController extends BaseController {

    @Autowired
    private UserExamService baseService;


    /**
     * 分页查找
     * @param reqDTO
     * @return
     */
    @ApiOperation(value = "分页查找")
    @RequestMapping(value = "/paging", method = { RequestMethod.POST})
    public ApiRest<IPage<UserExamRespDTO>> paging(@RequestBody PagingReqDTO<UserExamReqDTO> reqDTO) {

        //分页查询并转换
        IPage<UserExamRespDTO> page = baseService.paging(reqDTO);

        return super.success(page);
    }


    /**
    * 分页查找
    * @param reqDTO
    * @return
    */
    @ApiOperation(value = "分页查找")
    @RequestMapping(value = "/my-paging", method = { RequestMethod.POST})
    public ApiRest<IPage<UserExamRespDTO>> myPaging(@RequestBody PagingReqDTO<UserExamReqDTO> reqDTO) {

        //分页查询并转换
        IPage<UserExamRespDTO> page = baseService.myPaging(reqDTO);

        return super.success(page);
    }
}
