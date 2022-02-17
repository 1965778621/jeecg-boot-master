package org.jeecg.modules.demo.im.client;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author henry(zhanghai)
 * @date 2022-02-10 12:38
 */
@ApiModel(discriminator = "短信发送")
@Data
public class TencentSmsDTO {
    @ApiModelProperty(value = "tel")
    @NotNull(message = "tel不允许为空")
    private String tel;
}
