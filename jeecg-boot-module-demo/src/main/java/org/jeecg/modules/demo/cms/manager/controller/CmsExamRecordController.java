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
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.cms.manager.entity.CmsExamRecord;
import org.jeecg.modules.demo.cms.manager.service.ICmsExamRecordService;

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
 * @Description: 考试记录
 * @Author: jeecg-boot
 * @Date:   2022-02-15
 * @Version: V1.0
 */
@Api(tags="考试记录")
@RestController
@RequestMapping("/cms/manager/cmsExamRecord")
@Slf4j
public class CmsExamRecordController extends JeecgController<CmsExamRecord, ICmsExamRecordService> {
	@Autowired
	private ICmsExamRecordService cmsExamRecordService;

	/**
	 * 分页列表查询
	 *
	 * @param cmsExamRecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "考试记录-分页列表查询")
	@ApiOperation(value="考试记录-分页列表查询", notes="考试记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CmsExamRecord cmsExamRecord,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CmsExamRecord> queryWrapper = QueryGenerator.initQueryWrapper(cmsExamRecord, req.getParameterMap());
		Page<CmsExamRecord> page = new Page<CmsExamRecord>(pageNo, pageSize);
		IPage<CmsExamRecord> pageList = cmsExamRecordService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param cmsExamRecord
	 * @return
	 */
	@AutoLog(value = "考试记录-添加")
	@ApiOperation(value="考试记录-添加", notes="考试记录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CmsExamRecord cmsExamRecord) {
		cmsExamRecordService.save(cmsExamRecord);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param cmsExamRecord
	 * @return
	 */
	@AutoLog(value = "考试记录-编辑")
	@ApiOperation(value="考试记录-编辑", notes="考试记录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CmsExamRecord cmsExamRecord) {
		cmsExamRecordService.updateById(cmsExamRecord);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "考试记录-通过id删除")
	@ApiOperation(value="考试记录-通过id删除", notes="考试记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cmsExamRecordService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "考试记录-批量删除")
	@ApiOperation(value="考试记录-批量删除", notes="考试记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cmsExamRecordService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "考试记录-通过id查询")
	@ApiOperation(value="考试记录-通过id查询", notes="考试记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CmsExamRecord cmsExamRecord = cmsExamRecordService.getById(id);
		if(cmsExamRecord==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(cmsExamRecord);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param cmsExamRecord
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CmsExamRecord cmsExamRecord) {
        return super.exportXls(request, cmsExamRecord, CmsExamRecord.class, "考试记录");
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
        return super.importExcel(request, response, CmsExamRecord.class);
    }

}
