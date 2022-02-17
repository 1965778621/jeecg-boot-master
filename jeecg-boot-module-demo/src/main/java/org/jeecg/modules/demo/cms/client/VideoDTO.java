package org.jeecg.modules.demo.cms.client;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author henry(zhanghai)
 * @date 2022-02-14 14:04
 */
@Data
@ApiModel(discriminator = "记录观看视频记录")
public class VideoDTO {
    /**
     * id
     */
    @ApiModelProperty(value = "视频id不允许为空", required = true)
    @NotNull(message = "视频id不允许为空")
    private Integer id;

    /**
     * 当前播放时长
     */
    @ApiModelProperty(value = "当前播放时长", required = true)
    @NotNull(message = "当前播放时长不允许为空")
    private Long presentTime;

    /**
     * 当前播放时长
     */
    @ApiModelProperty(value = "当前播放时长", required = true)
    @NotNull(message = "当前播放时长不允许为空")
    private String presentTimeStr;

    /**
     * token
     */
    @ApiModelProperty(value = "token", required = true)
    @NotNull(message = "token不允许为空")
    private String token;
}
