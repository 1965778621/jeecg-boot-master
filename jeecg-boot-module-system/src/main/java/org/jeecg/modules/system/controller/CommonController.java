package org.jeecg.modules.system.controller;
import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.demo.im.client.FileInfoDTO;
import org.jeecg.modules.demo.utils.wechat.ConfigUtil;
import org.jeecg.modules.demo.utils.wechat.Constant;
import org.jeecg.modules.demo.utils.wechat.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
@Slf4j
@RestController
@RequestMapping("/sys/common")
public class CommonController {
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private ConfigUtil configUtil;
    /**
     * @Author 政辉
     * @return
     */
    @GetMapping("/403")
    public Result<?> noauth()  {
        return Result.error("没有权限，请联系管理员授权");
    }

    @PostMapping(value = "/upload")
    public Result<?> upload(MultipartFile file) {
        log.info(file+"file");
        String userId = "";
        try {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (sysUser != null) {
                userId = sysUser.getId();
            }
        } catch (Exception ignored) {
        }
        FileInfo fileInfo = fileStorageService.of(file)
                .setPath("/system/")
                .setObjectType("system")
                .setObjectId(userId)
                .upload();
        Result<?> result = new Result<>();
        result.setMessage(fileInfo.getUrl());
        result.setSuccess(true);
        log.info(result+"result");
        return result;
    }


}
