package org.jeecg.modules.demo.cms.client;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.cms.manager.entity.CmsTopic;
import org.jeecg.modules.demo.cms.manager.entity.CmsVideo;
import org.jeecg.modules.demo.cms.manager.entity.CmsVideoLog;
import org.jeecg.modules.demo.cms.manager.entity.TestPaperConfig;
import org.jeecg.modules.demo.cms.manager.mapper.CmsTopicMapper;
import org.jeecg.modules.demo.cms.manager.mapper.CmsVideoLogMapper;
import org.jeecg.modules.demo.cms.manager.mapper.TestPaperConfigMapper;
import org.jeecg.modules.demo.cms.manager.service.ICmsVideoLogService;
import org.jeecg.modules.demo.cms.manager.service.ITestPaperConfigService;
import org.jeecg.modules.demo.im.manager.entity.ImUser;
import org.jeecg.modules.demo.im.manager.mapper.ImUserMapper;
import org.jeecg.modules.demo.im.manager.service.IImUserService;
import org.jeecg.modules.demo.utils.wechat.Constant;
import org.jeecg.modules.demo.utils.wechat.Tools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * \* @author: ZH
 * \* Date:    2022/2/16/016
 * \* Time:    下午 3:02
 * \* Description:
 * \
 */
@Service
public class CmsTopicService extends ServiceImpl<CmsTopicMapper, CmsTopic> {
    @Resource
    private CmsVideoLogMapper cmsVideoLogMapper;
    @Resource
    private ITestPaperConfigService iTestPaperConfigService;
    @Resource
    private TestPaperConfigMapper testPaperConfigMapper;
    @Resource
    private CmsTopicMapper cmsTopicMapper;
    @Resource
    private ImUserMapper imUserMapper;

    /**
     * 获取题目列表
     *
     * @param videoCode 视频id
     * @param current   当前页
     * @param size      每页数量
     */
    public IPage<CmsTopic> list(long current, long size, String videoCode) {
        return page(Tools.page(current, size),
                new QueryWrapper<CmsTopic>()
                        .eq(CmsTopic.COL_DEL_FLAG, Constant.DELFLAG_NORMAL)
                        .eq(CmsTopic.COL_VIDEO_CODE, videoCode)
        );
    }

    /**
     * 1-判断视频时长是否满足 （插数据）
     * 2-获取考试的配置（类似公务考试 驾校考试） list
     * 3-进入考试(配置id考试类型 )
     * 4-更具配置id生成考试试卷（包括正确答案，题目解析），前端判断对错
     * list * 3 判断 单选 多选
     * <p>
     * 6-统计成绩（考试不合格 视频查看状态变为历史）
     * 后台-》
     * 1.及格 落库 可以继续学习 生成证书
     * 2.没及格 吧当前用户的所有观看过的视频状态置为历史 从头学 以观看过的视频不再累加观看时长
     */

    /**
     * 获取视频时长是否满足
     *
     * @param imUser   用户信息
     * @param cmsVideo 视频id
     * @return 1.问题判断用户是观看视频的总时长还是单个视频的时长
     * 2.考试测验还是当总时长超过60分钟后用户考过了开启下一个视频
     * 3.当前观看总时长累加各个视频观看时长的话 视频A考试考过了 视频B的表数据总时长为0？
     * 4.考试只有一次？考完就毕业？
     */
    public boolean getVideoWatchTime(ImUser imUser, CmsVideo cmsVideo) {
        //获取用户对应的视频表
        CmsVideoLog videoLog = cmsVideoLogMapper.getVideoLogByUserIdAndVideoId(imUser.getId(), cmsVideo.getId());
        if (videoLog.getTotalTime() > 10 * 60) {
            return true;
        }
        return false;
    }
    /**
     * //2-获取考试的配置（类似公务考试 驾校考试） list
     * //private Result<List<TestPaperConfig>> getTestPaperConfig
     * // 4-更具配置id生成考试试卷（包括正确答案，题目解析），前端判断对错
     * //     * list * 3 判断 单选 多选
     */

    /**
     * 生成考试试卷
     *
     * @param testPaperConfigId 试卷配置 ID
     * @return
     */
    public TestPaperDTO generateTestPaper(String testPaperConfigId) {
        TestPaperConfig testPaperConfig = testPaperConfigMapper.selectById(testPaperConfigId);
        //传给前端数据
        TestPaperDTO testPaperDTO = new TestPaperDTO();
        testPaperDTO.setJudgeTopFraction(testPaperConfig.getJudgeTopicScore());
        testPaperDTO.setSingleChoiceFraction(testPaperConfig.getSingleChoiceScore());
        testPaperDTO.setMultipleChoiceFraction(testPaperConfig.getMultipleChoiceScore());
        //单选题数量 1
        Integer singleChoiceNum = testPaperConfig.getSingleChoiceNum();
        //多选题数量 2
        Integer multipleChoiceNum = testPaperConfig.getMultipleChoiceNum();
        //判断题数量 3
        Integer judgeTopicNum = testPaperConfig.getJudgeTopicNum();
        //生成判断
        Random random = new Random();

        //配置获取题目
        if (singleChoiceNum > 0) {
            Assert.isTrue(cmsTopicMapper.getCount("1") > singleChoiceNum, "单选题不足，请联系管理员");
            int singleChoiceSum = cmsTopicMapper.getCount("1") - singleChoiceNum;
            if (singleChoiceSum > 0) {
                Page<CmsTopic> page = page(Tools.page(random.nextInt(singleChoiceSum), singleChoiceNum),
                        new QueryWrapper<CmsTopic>()
                                .eq(CmsTopic.COL_DEL_FLAG, Constant.DELFLAG_NORMAL)
                                .eq(CmsTopic.COL_TOPIC_TYPE, TopicTypeEnum.single_Choice.getValue()));
                List<CmsTopic> records = page.getRecords();
                testPaperDTO.setSingleChoiceList(records);
            }
        }
        Assert.isTrue(cmsTopicMapper.getCount("3") > judgeTopicNum, "判断选题不足，请联系管理员");
        Assert.isTrue(cmsTopicMapper.getCount("2") > multipleChoiceNum, "多选题不足，请联系管理员");

        int judgeTopicSum = cmsTopicMapper.getCount("3") - judgeTopicNum;
        int multipleChoiceSum = cmsTopicMapper.getCount("2") - multipleChoiceNum;


        if (judgeTopicSum > 0) {
            Page<CmsTopic> page = page(Tools.page(random.nextInt(judgeTopicSum), judgeTopicNum),
                    new QueryWrapper<CmsTopic>()
                            .eq(CmsTopic.COL_DEL_FLAG, Constant.DELFLAG_NORMAL)
                            .eq(CmsTopic.COL_TOPIC_TYPE, TopicTypeEnum.JUDGE_TOPIC.getValue()));
            List<CmsTopic> records = page.getRecords();
            testPaperDTO.setJudgeTopicList(records);
        }
        if (multipleChoiceSum > 0) {
            Page<CmsTopic> page = page(Tools.page(random.nextInt(multipleChoiceSum), multipleChoiceNum),
                    new QueryWrapper<CmsTopic>()
                            .eq(CmsTopic.COL_DEL_FLAG, Constant.DELFLAG_NORMAL)
                            .eq(CmsTopic.COL_TOPIC_TYPE, TopicTypeEnum.multiple_Choice.getValue()));
            List<CmsTopic> records = page.getRecords();
            testPaperDTO.setMultipleChoiceList(records);
        }


        testPaperDTO.setTestTime(testPaperConfig.getTestTime());
        return testPaperDTO;
    }

    /**
     * 6-统计成绩（考试不合格 视频查看状态变为历史）
     * * 后台-》(??及格判断)
     * * 1.及格 落库 可以继续学习 生成证书
     * * 2.没及格 吧当前用户的所有观看过的视频状态置为历史 从头学 以观看过的视频不再累加观看时长
     */
    @Transactional
    public UserCertificateDTO statistics(ImUser imUser, Integer score, String testId) {
        TestPaperConfig testPaperConfig = iTestPaperConfigService.getById(testId);
        double pass = testPaperConfig.getTotalPoints() * 0.6;
        // 考试通过
        if (score * 1.0 > pass) {
            UserCertificateDTO userCertificateDTO = new UserCertificateDTO();
            userCertificateDTO.setCompanyName(imUser.getCompanyName());
            userCertificateDTO.setId(imUser.getId());
            userCertificateDTO.setRealName(imUser.getRealName());
            List<CmsVideoLog> cmsVideoLogs = cmsVideoLogMapper.getVideoLogByUserId(imUser.getId());
            cmsVideoLogs.forEach(cmsVideoLog -> {
                cmsVideoLogMapper.changeGrade(cmsVideoLog.getId(), 100);
            });
            imUser.setExamResults(score);
            imUser.setCertificateStartTime(LocalDate.now());
            //date转localData
            imUser.setCertificateEndTime(yearPlusOne().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            imUserMapper.updateCertificate(imUser);
            return userCertificateDTO;
        } else {
            //没及格重置重头开始学
            List<CmsVideoLog> videoLogByUserId = cmsVideoLogMapper.getVideoLogByUserId(imUser.getId());
            videoLogByUserId.forEach(cmsVideoLog -> {
                cmsVideoLogMapper.changeGrade(cmsVideoLog.getId(), 1);
            });
            return null;
        }
    }

    /**
     * 年分加一
     */
    private Date yearPlusOne() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String format = now.format(fmt);
        Date parse = DateUtil.parse(format);
        return DateUtil.offset(parse, DateField.YEAR, 1);
    }
}
