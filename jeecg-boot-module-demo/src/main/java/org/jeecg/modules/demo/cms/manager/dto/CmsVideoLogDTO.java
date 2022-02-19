package org.jeecg.modules.demo.cms.manager.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author henry(zhanghai)
 * @date 2022-02-19 16:45
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cms_video_log对象", description="视频播放记录")
public class CmsVideoLogDTO {
    private static final long serialVersionUID = 1L;

    /**id*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
    /**视频ID*/
    @Excel(name = "视频ID", width = 15)
    @ApiModelProperty(value = "视频ID")
    private String videoId;
    /**标题*/
    @Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
    private String title;
    /**总时长*/
    @Excel(name = "总时长", width = 15)
    @ApiModelProperty(value = "总时长")
    private Long totalTime;
    /**当前播放时长*/
    @Excel(name = "当前播放时长", width = 15)
    @ApiModelProperty(value = "当前播放时长")
    private Long presentTime;
    /**当前播放时长*/
    @Excel(name = "当前播放时长", width = 15)
    @ApiModelProperty(value = "当前播放时长")
    private String presentTimeStr;
    /**是否跳转*/
    @Excel(name = "是否跳转", width = 15, dicCode = "video_type")
    @Dict(dicCode = "video_type")
    @ApiModelProperty(value = "是否跳转")
    private Integer tzType;
    /**跳转的url*/
    @Excel(name = "跳转的url", width = 15)
    @ApiModelProperty(value = "跳转的url")
    private String tzUrl;
    /**观看用户*/
    @Excel(name = "观看用户", width = 15)
    @ApiModelProperty(value = "观看用户")
    private Integer userId;
    /**观看日期*/
    @Excel(name = "观看日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "观看日期")
    private java.util.Date lookTime;
    /**删除标志*/
    @Excel(name = "删除标志", width = 15, dicCode = "del_flag")
    @Dict(dicCode = "del_flag")
    @ApiModelProperty(value = "删除标志")
    private Integer delFlag;

    /**
     * 是否重置 0正常 1是置为历史（视频可以再次观看记录）
     */
    @Excel(name = "是否重置", width = 15, dicCode = "if_text")
    @Dict(dicCode = "if_text")
    @ApiModelProperty(value = "是否重置")
    private Integer ifText;

    public static final String COL_DEL_FLAG = "del_flag";

    public static final String USER_ID = "user_id";
    /**封面*/
    @Excel(name = "封面", width = 15)
    @ApiModelProperty(value = "封面")
    private String img;
    /**地址*/
    @Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private String url;
}
