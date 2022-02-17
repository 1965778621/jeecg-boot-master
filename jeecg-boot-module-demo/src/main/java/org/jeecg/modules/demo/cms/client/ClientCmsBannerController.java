package org.jeecg.modules.demo.cms.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.demo.cms.manager.entity.CmsBanner;
import org.jeecg.modules.demo.cms.manager.entity.CmsVideo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * \* @author ZH
 * \* Date: 2022/2/15/015
 * \* Time: 上午 1:09
 * \* Description:
 * \
 *
 */
@Api(tags = "轮播图管理")
@RestController
@RequestMapping("/cms/client/cmsBanner")
@Slf4j
public class ClientCmsBannerController {
    @Resource
    private CmsBannerService cmsBannerService;
    @ApiOperation(value = "获取单个轮播图，先要获取轮播图列表", notes = "id:轮播图id")
    @GetMapping("/get")
    public Result<CmsBanner> get(Integer id) {
        return Result.OK(cmsBannerService.getById(id));
    }

    @ApiOperation(value = "获取轮播图列表（分页）", notes = "表单，type：类型，current：页码 -1查询全部")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码，默认1"),
            @ApiImplicitParam(name = "size", value = "每页数量，默认10")
    })
    @GetMapping("/list")
    public Result<IPage<CmsBanner>> list(@RequestParam(defaultValue = "1") long current,
                                         @RequestParam(defaultValue = "10") long size) {
        return Result.OK(cmsBannerService.list(current, size));
    }
}
