package org.jeecg.modules.demo.cms.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.cms.manager.entity.CmsExamination;
import org.jeecg.modules.demo.cms.manager.service.ICmsExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: 考试题库
 * @Author: jeecg-boot
 * @Date: 2022-02-13
 * @Version: V1.0
 */
@Api(tags = "考试题库")
@RestController
@RequestMapping("/cms/manager/cmsExamination")
@Slf4j
public class CmsExaminationController extends JeecgController<CmsExamination, ICmsExaminationService> {
    @Autowired
    private ICmsExaminationService cmsExaminationService;

    /**
     * 分页列表查询
     *
     * @param cmsExamination
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "考试题库-分页列表查询")
    @ApiOperation(value = "考试题库-分页列表查询", notes = "考试题库-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CmsExamination cmsExamination,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<CmsExamination> queryWrapper = QueryGenerator.initQueryWrapper(cmsExamination, req.getParameterMap());
        Page<CmsExamination> page = new Page<CmsExamination>(pageNo, pageSize);
        IPage<CmsExamination> pageList = cmsExaminationService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param cmsExamination
     * @return
     */
    @AutoLog(value = "考试题库-添加")
    @ApiOperation(value = "考试题库-添加", notes = "考试题库-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CmsExamination cmsExamination) {
        cmsExaminationService.save(cmsExamination);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param cmsExamination
     * @return
     */
    @AutoLog(value = "考试题库-编辑")
    @ApiOperation(value = "考试题库-编辑", notes = "考试题库-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CmsExamination cmsExamination) {
        cmsExaminationService.updateById(cmsExamination);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "考试题库-通过id删除")
    @ApiOperation(value = "考试题库-通过id删除", notes = "考试题库-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        cmsExaminationService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "考试题库-批量删除")
    @ApiOperation(value = "考试题库-批量删除", notes = "考试题库-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.cmsExaminationService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "考试题库-通过id查询")
    @ApiOperation(value = "考试题库-通过id查询", notes = "考试题库-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CmsExamination cmsExamination = cmsExaminationService.getById(id);
        if (cmsExamination == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(cmsExamination);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param cmsExamination
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CmsExamination cmsExamination) {
        return super.exportXls(request, cmsExamination, CmsExamination.class, "考试题库");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CmsExamination.class);
    }

}
