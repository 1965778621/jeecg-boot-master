package org.jeecg.modules.demo.cms.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.demo.cms.manager.entity.TestPaperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* @author: ZH
 * \* Date:    2022/2/18/018
 * \* Time:    下午 2:55
 * \* Description:
 * \
 */
@Api(tags = "考试类型")
@RestController
@RequestMapping("/cms/client/testPaperConfig")
@Slf4j
public class ClientCmsTestPaperConfigController {
    @Autowired
    CmsTestPaperConfigService cmsTestPaperConfigService;

    @ApiOperation(value = "获取考试类型列表（分页）", notes = "表单，type：类型，current：页码 -1查询全部")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码，默认1"),
            @ApiImplicitParam(name = "size", value = "每页数量，默认10")
    })
    @GetMapping("/list")
    public Result<IPage<TestPaperConfig>> list(@RequestParam(defaultValue = "1") long current,
                                               @RequestParam(defaultValue = "10") long size) {
        return Result.OK(cmsTestPaperConfigService.list(current, size));
    }
}
