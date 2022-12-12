package com.yf.exam.modules.sys.depart.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yf.exam.core.api.ApiRest;
import com.yf.exam.core.api.controller.BaseController;
import com.yf.exam.core.api.dto.BaseIdReqDTO;
import com.yf.exam.core.api.dto.BaseIdsReqDTO;
import com.yf.exam.core.api.dto.PagingReqDTO;
import com.yf.exam.core.utils.BeanMapper;
import com.yf.exam.modules.sys.depart.dto.SysDepartDTO;
import com.yf.exam.modules.sys.depart.dto.request.DepartSortReqDTO;
import com.yf.exam.modules.sys.depart.dto.response.SysDepartTreeDTO;
import com.yf.exam.modules.sys.depart.entity.SysDepart;
import com.yf.exam.modules.sys.depart.service.SysDepartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* <p>
* 部门信息控制器
* </p>
*
* @author 聪明笨狗
* @since 2020-09-02 17:25
*/
@Api(tags={"部门信息"})
@RestController
@RequestMapping("/exam/api/sys/depart")
public class SysDepartController extends BaseController {

    @Autowired
    private SysDepartService baseService;

    /**
    * 添加或修改
    * @param reqDTO
    * @return
    */
    @ApiOperation(value = "添加或修改")
    @RequestMapping(value = "/save", method = { RequestMethod.POST})
    public ApiRest save(@RequestBody SysDepartDTO reqDTO) {
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
    public ApiRest<SysDepartDTO> find(@RequestBody BaseIdReqDTO reqDTO) {
        SysDepart entity = baseService.getById(reqDTO.getId());
        SysDepartDTO dto = new SysDepartDTO();
        BeanUtils.copyProperties(entity, dto);
        return super.success(dto);
    }

    /**
    * 分页查找
    * @param reqDTO
    * @return
    */
    @ApiOperation(value = "分页查找")
    @RequestMapping(value = "/paging", method = { RequestMethod.POST})
    public ApiRest<IPage<SysDepartTreeDTO>> paging(@RequestBody PagingReqDTO<SysDepartDTO> reqDTO) {

        //分页查询并转换
        IPage<SysDepartTreeDTO> page = baseService.paging(reqDTO);

        return super.success(page);
    }

    /**
     * 查找列表，每次最多返回200条数据
     * @param reqDTO
     * @return
     */
    @ApiOperation(value = "查找列表")
    @RequestMapping(value = "/list", method = { RequestMethod.POST})
    public ApiRest<List<SysDepartDTO>> list(@RequestBody SysDepartDTO reqDTO) {

        //分页查询并转换
        QueryWrapper<SysDepart> wrapper = new QueryWrapper<>();

        //转换并返回
        List<SysDepart> list = baseService.list(wrapper);

        //转换数据
        List<SysDepartDTO> dtoList = BeanMapper.mapList(list, SysDepartDTO.class);

        return super.success(dtoList);
    }


    /**
     * 树列表
     * @return
     */
    @ApiOperation(value = "树列表")
    @RequestMapping(value = "/tree", method = { RequestMethod.POST})
    public ApiRest<List<SysDepartTreeDTO>> tree() {
        List<SysDepartTreeDTO> dtoList = baseService.findTree();
        return super.success(dtoList);
    }


    /**
     * 分类排序
     * @param reqDTO
     * @return
     */
    @ApiOperation(value = "分类排序")
    @RequestMapping(value = "/sort", method = { RequestMethod.POST})
    public ApiRest sort(@RequestBody DepartSortReqDTO reqDTO) {
        baseService.sort(reqDTO.getId(), reqDTO.getSort());
        return super.success();
    }
}
