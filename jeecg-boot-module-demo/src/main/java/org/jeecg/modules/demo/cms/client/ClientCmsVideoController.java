package org.jeecg.modules.demo.cms.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.demo.cms.manager.entity.CmsVideo;
import org.jeecg.modules.demo.cms.manager.entity.CmsVideoLog;
import org.jeecg.modules.demo.cms.manager.service.ICmsVideoLogService;
import org.jeecg.modules.demo.im.manager.entity.ImUser;
import org.jeecg.modules.demo.im.manager.mapper.ImUserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author henry(zhanghai)
 * @date 2022-02-10 13:16
 */
@Api(tags = "视频管理")
@RestController
@RequestMapping("/cms/client/cmsVideo")
@Slf4j
public class ClientCmsVideoController {
    @Resource
    private CmsVideoService cmsVideoService;
    @Resource
    private ClientVideoLogService logService;
    @Resource
    private ImUserMapper imUserMapper;
    @Resource
    private ICmsVideoLogService cmsVideoLogService;

    @ApiOperation(value = "获取单个视频，先要获取视频列表", notes = "id:视频id")
    @GetMapping("/get")
    public Result<CmsVideo> get(Integer id) {
        return Result.OK(cmsVideoService.getById(id));
    }

    @ApiOperation(value = "获取视频列表（分页）", notes = "表单，type：类型，current：页码 -1查询全部")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码，默认1"),
            @ApiImplicitParam(name = "size", value = "每页数量，默认10")
    })
    @GetMapping("/list")
    public Result<IPage<CmsVideo>> list(@RequestParam(defaultValue = "1") long current,
                                        @RequestParam(defaultValue = "10") long size
    ) {
        return Result.OK(cmsVideoService.list(current, size));
    }

    @ApiOperation(value = "获取视频观看列表（分页）", notes = "表单，type：类型，current：页码 -1查询全部")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码，默认1"),
            @ApiImplicitParam(name = "size", value = "每页数量，默认10"),
            @ApiImplicitParam(name = "token", value = "token谁看的")
    })

    @GetMapping("/listLog")
    public Result<IPage<CmsVideoLog>> listLog(@RequestParam(defaultValue = "1") long current,
                                              @RequestParam(defaultValue = "10") long size,
                                              @RequestParam(defaultValue = "10") String token
    ) {
        ImUser imUserSelect = imUserMapper.getToken(token);
        return Result.OK(logService.listLog(current, size, imUserSelect.getId()));
    }

    @ApiOperation(value = "视频保存事件", notes = "表单，Token")
    @PostMapping("/end")
    public Result<?> end(@RequestBody VideoDTO videoDTO) {
        Integer checkUserToken = imUserMapper.checkToken(videoDTO.getToken());
        if (checkUserToken == 0) {
            return Result.error520("token不存在或已经过期了！");
        }
        ImUser imUserSelect = imUserMapper.getToken(videoDTO.getToken());
        CmsVideo ad = cmsVideoService.getById(videoDTO.getId());
        CmsVideoLog cmsVideoLog = new CmsVideoLog();
        cmsVideoLog.setVideoId(videoDTO.getId());
        cmsVideoLog.setPresentTime(videoDTO.getPresentTime());
        cmsVideoLog.setPresentTimeStr(videoDTO.getPresentTimeStr());
        cmsVideoLog.setTitle(ad.getTitle());
        cmsVideoLog.setUserId(imUserSelect.getId());
        cmsVideoLog.setTzType(ad.getTzType());
        cmsVideoLog.setTzUrl(ad.getTzUrl());
        cmsVideoLog.setLookTime(new Date());
        boolean b = true;
        if (b) {
            cmsVideoLogService.save(cmsVideoLog);
        }
        return Result.auto(b);
    }
}
