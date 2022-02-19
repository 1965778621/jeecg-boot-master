package org.jeecg.modules.demo.cms.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.demo.cms.manager.entity.CmsTopic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Resource
    private CmsTopicService cmsTopicService;
    @ApiOperation(value = "获取单个视频的题目，每次获取10个")
    @GetMapping("/list")
    public Result<IPage<CmsTopic>> list(@RequestParam(defaultValue = "1") long current,
                                        @RequestParam(defaultValue = "10") long size,
                                        @NotNull String valueCode) {
        /**
         * 1-判断视频时长是否满足 （插数据）
         * 2-获取考试的配置（类似公务考试 驾校考试） list
         * 3-进入考试(配置id考试类型)
         * 4-更具配置id生成考试试卷（包括正确答案，题目解析），前端判断对错
         *      list * 3 判断 单选 多选
         *
         * 5-考试结束
         *  1.时间到了自动交卷
         *  2.答题完毕交卷
         * 6-统计成绩（考试不合格 视频查看状态变为历史）
         *      后台-》
         *          1.及格 落库 可以继续学习 生成证书
         *          2.没及格 吧当前用户的所有观看过的视频状态置为历史 从头学 以观看过的视频不再累加观看时长
         *
         *
         */
        return Result.OK(cmsTopicService.list(current, size,valueCode));
    }
    @ApiOperation(value = "考试题库")
    @GetMapping("/add")
    public Result<TestPaperDTO> testPaperDTO(){
        TestPaperDTO testPaperDTO = cmsTopicService.generateTestPaper("1");
        System.out.println("testPaperDTO = " + testPaperDTO);
        return Result.OK(testPaperDTO);
    }
}
