package com.yf.exam.modules.exam.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yf.exam.core.api.ApiRest;
import com.yf.exam.core.api.controller.BaseController;
import com.yf.exam.core.api.dto.BaseIdReqDTO;
import com.yf.exam.core.api.dto.BaseIdsReqDTO;
import com.yf.exam.core.api.dto.BaseStateReqDTO;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.modules.exam.dto.ExamDTO;
import com.yf.exam.modules.exam.dto.request.ExamSaveReqDTO;
import com.yf.exam.modules.exam.dto.response.ExamOnlineRespDTO;
import com.yf.exam.modules.exam.dto.response.ExamReviewRespDTO;
import com.yf.exam.modules.exam.entity.Exam;
import com.yf.exam.modules.exam.service.ExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

/**
* <p>
* 考试控制器
* </p>
*
* @author 聪明笨狗
* @since 2020-07-25 16:18
*/
@Api(tags={"考试"})
@RestController
@RequestMapping("/exam/api/exam/exam")
public class ExamController extends BaseController {

    @Autowired
    private ExamService baseService;
    /**
    * 添加或修改
    * @param reqDTO
    * @return
    */
    @ApiOperation(value = "添加或修改")
    @RequestMapping(value = "/save", method = { RequestMethod.POST})
    public ApiRest save(@RequestBody ExamSaveReqDTO reqDTO) {
        //复制参数
        baseService.save(reqDTO);
        return super.success();
    }

    /**
    * 批量删除
    * @param reqDTO
    * @return
    */
    @ApiOperation(value = "批量删除")
    @RequestMapping(value = "/delete", method = { RequestMethod.POST})
    public ApiRest edit(@RequestBody BaseIdsReqDTO reqDTO) {
        //根据ID删除
        baseService.removeByIds(reqDTO.getIds());
        return super.success();
    }

    /**
    * 查找详情
    * @param reqDTO
    * @return
    */
    @ApiOperation(value = "查找详情")
    @RequestMapping(value = "/detail", method = { RequestMethod.POST})
    public ApiRest<ExamSaveReqDTO> find(@RequestBody BaseIdReqDTO reqDTO) {
        ExamSaveReqDTO dto = baseService.findDetail(reqDTO.getId());
        return super.success(dto);
    }

    /**
     * 查找详情
     * @param reqDTO
     * @return
     */
    @ApiOperation(value = "查找详情")
    @RequestMapping(value = "/state", method = { RequestMethod.POST})
    public ApiRest state(@RequestBody BaseStateReqDTO reqDTO) {

        QueryWrapper<Exam> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(Exam::getId, reqDTO.getIds());
        Exam exam = new Exam();
        exam.setState(reqDTO.getState());
        exam.setUpdateTime(new Date());

        baseService.update(exam, wrapper);
        return super.success();
    }


    /**
     * 分页查找
     * @param reqDTO
     * @return
     */
    @ApiOperation(value = "考试视角")
    @RequestMapping(value = "/online-paging", method = { RequestMethod.POST})
    public ApiRest<IPage<ExamOnlineRespDTO>> myPaging(@RequestBody PagingReqDTO<ExamDTO> reqDTO) {

        //分页查询并转换
        IPage<ExamOnlineRespDTO> page = baseService.onlinePaging(reqDTO);
        return super.success(page);
    }

    /**
    * 分页查找
    * @param reqDTO
    * @return
    */
    @ApiOperation(value = "分页查找")
    @RequestMapping(value = "/paging", method = { RequestMethod.POST})
    public ApiRest<IPage<ExamDTO>> paging(@RequestBody PagingReqDTO<ExamDTO> reqDTO) {

        //分页查询并转换
        IPage<ExamDTO> page = baseService.paging(reqDTO);

        return super.success(page);
    }


    /**
     * 分页查找
     * @param reqDTO
     * @return
     */
    @ApiOperation(value = "待阅试卷")
    @RequestMapping(value = "/review-paging", method = { RequestMethod.POST})
    public ApiRest<IPage<ExamReviewRespDTO>> reviewPaging(@RequestBody PagingReqDTO<ExamDTO> reqDTO) {

        //分页查询并转换
        IPage<ExamReviewRespDTO> page = baseService.reviewPaging(reqDTO);

        return super.success(page);
    }


}
