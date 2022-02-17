package org.jeecg.modules.demo.im.client;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author henry(zhanghai)
 * @date 2022-02-09 1:02
 */
@ApiModel(discriminator = "token")
@Data
public class ImUserTokenDTO {
    @ApiModelProperty(value = "token")
    @NotNull(message = "token不允许为空")
    private String token;
}
