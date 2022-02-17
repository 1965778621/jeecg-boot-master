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
 * @Description: 视频
 * @Author: jeecg-boot
 * @Date:   2022-02-13
 * @Version: V1.0
 */
@Data
@TableName("cms_video")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cms_video对象", description="视频")
public class CmsVideo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**视频id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "视频id")
    private java.lang.String id;
	/**标题*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
    private java.lang.String title;
	/**时长(单位秒)*/
	@Excel(name = "时长(单位秒)", width = 15)
    @ApiModelProperty(value = "时长(单位秒)")
    private java.lang.Long time;
	/**封面*/
	@Excel(name = "封面", width = 15)
    @ApiModelProperty(value = "封面")
    private java.lang.String img;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private java.lang.String url;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**删除标志*/
	@Excel(name = "删除标志", width = 15, dicCode = "del_flag")
	@Dict(dicCode = "del_flag")
    @ApiModelProperty(value = "删除标志")
    private java.lang.Integer delFlag;
	/**是否跳转*/
	@Excel(name = "是否跳转", width = 15, dicCode = "video_type")
	@Dict(dicCode = "video_type")
    @ApiModelProperty(value = "是否跳转")
    private java.lang.Integer tzType;
	/**跳转的url*/
	@Excel(name = "跳转的url", width = 15)
    @ApiModelProperty(value = "跳转的url")
    private java.lang.String tzUrl;
	/**视频超链接*/
	@Excel(name = "视频超链接", width = 15)
    @ApiModelProperty(value = "视频超链接")
    private java.lang.String urldetail;
    public static final String COL_DEL_FLAG = "del_flag";
}
