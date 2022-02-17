package org.jeecg.modules.demo.im.client;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author henry(zhanghai)
 * @date 2022-02-15 19:48
 */
@ApiModel(discriminator = "token")
@Data
public class TelAndCodeDTO {
    @ApiModelProperty(value = "tel")
    @NotNull(message = "手机号码不允许为空")
    private String tel;
    @ApiModelProperty(value = "code")
    @NotNull(message = "验证码不允许为空")
    private String code;
}
