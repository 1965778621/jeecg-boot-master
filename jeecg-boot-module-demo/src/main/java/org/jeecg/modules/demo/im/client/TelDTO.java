package org.jeecg.modules.demo.im.client;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author henry(zhanghai)
 * @date 2022-02-15 19:46
 */
@ApiModel(discriminator = "token")
@Data
public class TelDTO {
    @ApiModelProperty(value = "tel")
    @NotNull(message = "手机号码不允许为空")
    private String tel;
}
