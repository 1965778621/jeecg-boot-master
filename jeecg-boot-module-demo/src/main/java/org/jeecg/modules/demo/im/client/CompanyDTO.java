package org.jeecg.modules.demo.im.client;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author henry(zhanghai)
 * @date 2022-02-09 1:43
 */
@Data
@ApiModel(discriminator = "用户信息更新")
public class CompanyDTO {
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名", required = true)
    @NotNull(message = "真实姓名不允许为空")
    private String realName;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", required = true)
    @NotNull(message = "手机号码不允许为空")
    private String tel;
    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称", required = true)
    @NotNull(message = "公司名称不允许为空")
    private String companyName;

    /**
     * 公司职位
     */
    @ApiModelProperty(value = "公司职位", required = true)
    @NotNull(message = "公司职位不允许为空")
    private String companyPosition;

    /**
     * 印刷字号(公司税务号)
     */
    @ApiModelProperty(value = "印刷字号(公司税务号)", required = true)
    @NotNull(message = "印刷字号(公司税务号)不允许为空")
    private String companyNo;

    /**
     * 证件照
     */
    @ApiModelProperty(value = "证件照", required = true)
    @NotNull(message = "证件照不允许为空")
    private String identificationPhoto;

    /**
     * token
     */
    @ApiModelProperty(value = "token")
    @NotNull(message = "token不允许为空")
    private String token;

    /**
     * code
     */
    @ApiModelProperty(value = "code")
    @NotNull(message = "验证码不允许为空")
    private String code;
}
