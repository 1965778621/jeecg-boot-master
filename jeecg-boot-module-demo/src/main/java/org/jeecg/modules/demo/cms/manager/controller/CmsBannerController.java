package org.jeecg.modules.demo.cms.manager.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.cms.manager.entity.CmsBanner;
import org.jeecg.modules.demo.cms.manager.service.ICmsBannerService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 轮播图
 * @Author: jeecg-boot
 * @Date:   2022-02-15
 * @Version: V1.0
 */
@Api(tags="轮播图")
@RestController
@RequestMapping("/cms/manager/cmsBanner")
@Slf4j
public class CmsBannerController extends JeecgController<CmsBanner, ICmsBannerService> {
	@Autowired
	private ICmsBannerService cmsBannerService;

	/**
	 * 分页列表查询
	 *
	 * @param cmsBanner
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "轮播图-分页列表查询")
	@ApiOperation(value="轮播图-分页列表查询", notes="轮播图-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CmsBanner cmsBanner,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CmsBanner> queryWrapper = QueryGenerator.initQueryWrapper(cmsBanner, req.getParameterMap());
		Page<CmsBanner> page = new Page<CmsBanner>(pageNo, pageSize);
		IPage<CmsBanner> pageList = cmsBannerService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param cmsBanner
	 * @return
	 */
	@AutoLog(value = "轮播图-添加")
	@ApiOperation(value="轮播图-添加", notes="轮播图-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CmsBanner cmsBanner) {
		cmsBannerService.save(cmsBanner);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param cmsBanner
	 * @return
	 */
	@AutoLog(value = "轮播图-编辑")
	@ApiOperation(value="轮播图-编辑", notes="轮播图-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CmsBanner cmsBanner) {
		cmsBannerService.updateById(cmsBanner);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "轮播图-通过id删除")
	@ApiOperation(value="轮播图-通过id删除", notes="轮播图-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cmsBannerService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "轮播图-批量删除")
	@ApiOperation(value="轮播图-批量删除", notes="轮播图-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cmsBannerService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "轮播图-通过id查询")
	@ApiOperation(value="轮播图-通过id查询", notes="轮播图-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CmsBanner cmsBanner = cmsBannerService.getById(id);
		if(cmsBanner==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(cmsBanner);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param cmsBanner
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CmsBanner cmsBanner) {
        return super.exportXls(request, cmsBanner, CmsBanner.class, "轮播图");
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
        return super.importExcel(request, response, CmsBanner.class);
    }

}
