package org.jeecg.modules.demo.im.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.im.client.CompanyDTO;
import org.jeecg.modules.demo.im.manager.entity.ImUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: im_user
 * @Author: jeecg-boot
 * @Date:   2022-02-08
 * @Version: V1.0
 */
public interface ImUserMapper extends BaseMapper<ImUser> {
    boolean delete(int id);
    int checkOpenid(@Param(value = "openidMphelper") String openidMphelper);

    int updToken(ImUser imUser);

    int updImUser(ImUser imUser);

    ImUser getImUser(@Param(value = "openidMphelper") String openidMphelper);

    ImUser getToken(@Param(value = "token") String token);

    int checkToken(@Param(value = "token") String token);

    int is_token_valid(@Param(value = "token") String token);

    int updImUserCompany(ImUser imUser);
}
