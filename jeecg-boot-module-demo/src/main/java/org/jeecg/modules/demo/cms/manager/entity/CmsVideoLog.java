package org.jeecg.modules.demo.cms.manager.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 视频播放记录
 * @Author: jeecg-boot
 * @Date:   2022-02-10
 * @Version: V1.0
 */
@Data
@TableName("cms_video_log")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cms_video_log对象", description="视频播放记录")
public class CmsVideoLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**id*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
    /**视频ID*/
    @Excel(name = "视频ID", width = 15)
    @ApiModelProperty(value = "视频ID")
    private java.lang.Integer videoId;
    /**标题*/
    @Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
    private java.lang.String title;
    /**总时长*/
    @Excel(name = "总时长", width = 15)
    @ApiModelProperty(value = "总时长")
    private java.lang.Long totalTime;
    /**当前播放时长*/
    @Excel(name = "当前播放时长", width = 15)
    @ApiModelProperty(value = "当前播放时长")
    private java.lang.Long presentTime;
    /**当前播放时长*/
    @Excel(name = "当前播放时长", width = 15)
    @ApiModelProperty(value = "当前播放时长")
    private java.lang.String presentTimeStr;
    /**是否跳转*/
    @Excel(name = "是否跳转", width = 15, dicCode = "video_type")
    @Dict(dicCode = "video_type")
    @ApiModelProperty(value = "是否跳转")
    private java.lang.Integer tzType;
    /**跳转的url*/
    @Excel(name = "跳转的url", width = 15)
    @ApiModelProperty(value = "跳转的url")
    private java.lang.String tzUrl;
    /**观看用户*/
    @Excel(name = "观看用户", width = 15)
    @ApiModelProperty(value = "观看用户")
    private java.lang.Integer userId;
    /**观看日期*/
    @Excel(name = "观看日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "观看日期")
    private java.util.Date lookTime;
    public static final String COL_DEL_FLAG = "del_flag";

    public static final String USER_ID = "userId";
}
