package org.jeecg.modules.demo.im.manager.controller;

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
import org.jeecg.modules.demo.im.manager.entity.ImUser;
import org.jeecg.modules.demo.im.manager.service.IImUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: im_user
 * @Author: jeecg-boot
 * @Date: 2022-02-08
 * @Version: V1.0
 */
@Api(tags = "im_user")
@RestController
@RequestMapping("/im/manager/imUser")
@Slf4j
public class ImUserController extends JeecgController<ImUser, IImUserService> {
    @Autowired
    private IImUserService imUserService;

    /**
     * 分页列表查询
     *
     * @param imUser
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "im_user-分页列表查询")
    @ApiOperation(value = "im_user-分页列表查询", notes = "im_user-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ImUser imUser,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ImUser> queryWrapper = QueryGenerator.initQueryWrapper(imUser, req.getParameterMap());
        Page<ImUser> page = new Page<ImUser>(pageNo, pageSize);
        IPage<ImUser> pageList = imUserService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param imUser
     * @return
     */
    @AutoLog(value = "im_user-添加")
    @ApiOperation(value = "im_user-添加", notes = "im_user-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ImUser imUser) {
        imUserService.save(imUser);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param imUser
     * @return
     */
    @AutoLog(value = "im_user-编辑")
    @ApiOperation(value = "im_user-编辑", notes = "im_user-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ImUser imUser) {
        imUserService.updateById(imUser);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "im_user-通过id删除")
    @ApiOperation(value = "im_user-通过id删除", notes = "im_user-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
//        if (imUserService.remove(Integer.parseInt(id))) {
//            return Result.OK("删除成功!");
//        }
//
		imUserService.removeById(id);
        return Result.OK("删除失败!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "im_user-批量删除")
    @ApiOperation(value = "im_user-批量删除", notes = "im_user-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.imUserService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "im_user-通过id查询")
    @ApiOperation(value = "im_user-通过id查询", notes = "im_user-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ImUser imUser = imUserService.getById(id);
        if (imUser == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(imUser);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param imUser
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ImUser imUser) {
        return super.exportXls(request, imUser, ImUser.class, "im_user");
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
        return super.importExcel(request, response, ImUser.class);
    }

}
