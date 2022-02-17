package org.jeecg.modules.demo.im.manager.service.impl;
import org.jeecg.modules.demo.im.manager.entity.ImUser;
import org.jeecg.modules.demo.im.manager.mapper.ImFileMapper;
import org.jeecg.modules.demo.im.manager.mapper.ImUserMapper;
import org.jeecg.modules.demo.im.manager.service.IImUserService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Reader;


/**
 * @Description: im_user
 * @Author: jeecg-boot
 * @Date:   2022-02-08
 * @Version: V1.0
 */
@Service
public class ImUserServiceImpl extends ServiceImpl<ImUserMapper, ImUser> implements IImUserService {

}
