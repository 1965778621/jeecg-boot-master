package org.jeecg.modules.demo.cms.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.demo.cms.manager.entity.CmsTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Random;

/**
 * \* @author: ZH
 * \* Date:    2022/2/16/016
 * \* Time:    下午 2:56
 * \* Description:
 * \
 */
@Api(tags = "考试题库")
@RestController
@RequestMapping("/cms/client/cmsTopic")
@Slf4j
public class ClientCmsTopicController {

    @Autowired
    private CmsTopicService cmsTopicService;
    @ApiOperation(value = "获取单个视频的题目，每次获取10个")
    @GetMapping("/list")
    public Result<IPage<CmsTopic>> list(@RequestParam(defaultValue = "1") long current,
                                        @RequestParam(defaultValue = "10") long size,
                                        @NotNull String valueCode) {
        /**
         * 逻辑如下
         * 1.先判断该用户是否看了该视频 在视频记录表内查找
         */
        int count = cmsTopicService.count()-10;
        Random random = new Random();
        return Result.OK(cmsTopicService.list(random.nextInt(count), size,valueCode));
    }

}
