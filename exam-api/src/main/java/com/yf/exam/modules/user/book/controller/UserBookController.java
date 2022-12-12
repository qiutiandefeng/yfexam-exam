package com.yf.exam.modules.user.book.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yf.exam.core.api.ApiRest;
import com.yf.exam.core.api.controller.BaseController;
import com.yf.exam.core.api.dto.BaseIdRespDTO;
import com.yf.exam.core.api.dto.BaseIdsReqDTO;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.modules.user.book.dto.UserBookDTO;
import com.yf.exam.modules.user.book.service.UserBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 错题本控制器
* </p>
*
* @author 聪明笨狗
* @since 2020-05-27 17:56
*/
@Api(tags={"错题本"})
@RestController
@RequestMapping("/exam/api/user/wrong-book")
public class UserBookController extends BaseController {

    @Autowired
    private UserBookService baseService;


    /**
    * 批量删除
    * @param reqDTO
    * @return
    */
    @ApiOperation(value = "批量删除")
    @RequestMapping(value = "/delete", method = { RequestMethod.POST})
    public ApiRest delete(@RequestBody BaseIdsReqDTO reqDTO) {
        //根据ID删除
        baseService.removeByIds(reqDTO.getIds());
        return super.success();
    }

    /**
    * 分页查找
    * @param reqDTO
    * @return
    */
    @ApiOperation(value = "分页查找")
    @RequestMapping(value = "/paging", method = { RequestMethod.POST})
    public ApiRest<IPage<UserBookDTO>> paging(@RequestBody PagingReqDTO<UserBookDTO> reqDTO) {

        //分页查询并转换
        IPage<UserBookDTO> page = baseService.paging(reqDTO);

        return super.success(page);
    }

    /**
     * 查找列表，每次最多返回200条数据
     * @param reqDTO
     * @return
     */
    @ApiOperation(value = "查找列表")
    @RequestMapping(value = "/next", method = { RequestMethod.POST})
    public ApiRest<BaseIdRespDTO> nextQu(@RequestBody UserBookDTO reqDTO) {
        //转换并返回
        String quId = baseService.findNext(reqDTO.getExamId(), reqDTO.getQuId());
        return super.success(new BaseIdRespDTO(quId));
    }
}
