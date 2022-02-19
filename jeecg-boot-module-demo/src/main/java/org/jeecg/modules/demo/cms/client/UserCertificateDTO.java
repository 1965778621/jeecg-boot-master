package org.jeecg.modules.demo.cms.client;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * \* @author: ZH
 * \* Date:    2022/2/19
 * \* Time:    20:53
 * \* Description:
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCertificateDTO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "公司名称")
    private String companyName;


}
