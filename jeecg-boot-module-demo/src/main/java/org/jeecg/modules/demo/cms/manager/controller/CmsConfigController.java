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
import org.jeecg.modules.demo.cms.manager.entity.CmsConfig;
import org.jeecg.modules.demo.cms.manager.mapper.CmsConfigMapper;
import org.jeecg.modules.demo.cms.manager.service.ICmsConfigService;
import org.jeecg.modules.demo.cms.manager.sysconfig.ISysConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 参数配置
 * @Author: jeecg-boot
 * @Date: 2022-02-09
 * @Version: V1.0
 */
@Api(tags = "参数配置")
@RestController
@RequestMapping("/cms/manager/cmsConfig")
@Slf4j
public class CmsConfigController extends JeecgController<CmsConfig, ICmsConfigService> {
    @Autowired
    private ICmsConfigService cmsConfigService;
    @Autowired
    private ISysConfigUtil iSysConfigUtil;
    @Resource
    private CmsConfigMapper cmsConfigMapper;

    /**
     * 分页列表查询
     *
     * @param cmsConfig
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "参数配置-分页列表查询")
    @ApiOperation(value = "参数配置-分页列表查询", notes = "参数配置-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CmsConfig cmsConfig,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<CmsConfig> queryWrapper = QueryGenerator.initQueryWrapper(cmsConfig, req.getParameterMap());
        Page<CmsConfig> page = new Page<CmsConfig>(pageNo, pageSize);
        IPage<CmsConfig> pageList = cmsConfigService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param cmsConfig
     * @return
     */
    @AutoLog(value = "参数配置-添加")
    @ApiOperation(value = "参数配置-添加", notes = "参数配置-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CmsConfig cmsConfig) {
        cmsConfigService.save(cmsConfig);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param cmsConfig
     * @return
     */
    @AutoLog(value = "参数配置-编辑")
    @ApiOperation(value = "参数配置-编辑", notes = "参数配置-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CmsConfig cmsConfig) {
        cmsConfigService.updateById(cmsConfig);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "参数配置-通过id删除")
    @ApiOperation(value = "参数配置-通过id删除", notes = "参数配置-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        cmsConfigService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "参数配置-批量删除")
    @ApiOperation(value = "参数配置-批量删除", notes = "参数配置-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.cmsConfigService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "参数配置-通过id查询")
    @ApiOperation(value = "参数配置-通过id查询", notes = "参数配置-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CmsConfig cmsConfig = cmsConfigService.getById(id);
        if (cmsConfig == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(cmsConfig);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param cmsConfig
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CmsConfig cmsConfig) {
        return super.exportXls(request, cmsConfig, CmsConfig.class, "参数配置");
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
        return super.importExcel(request, response, CmsConfig.class);
    }

}
