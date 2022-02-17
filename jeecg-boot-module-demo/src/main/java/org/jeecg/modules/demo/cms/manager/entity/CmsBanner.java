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
 * @Description: 轮播图
 * @Author: jeecg-boot
 * @Date:   2022-02-15
 * @Version: V1.0
 */
@Data
@TableName("cms_banner")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cms_banner对象", description="轮播图")
public class CmsBanner implements Serializable {
    private static final long serialVersionUID = 1L;

	/**轮播图id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "轮播图id")
    private java.lang.String id;
	/**标题*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
    private java.lang.String title;
	/**轮播图编组*/
	@Excel(name = "轮播图编组", width = 15)
    @ApiModelProperty(value = "轮播图编组")
    private java.lang.String series;
	/**类型*/
	@Excel(name = "类型", width = 15)
    @ApiModelProperty(value = "类型")
    private java.lang.Integer type;
	/**跳转的url*/
	@Excel(name = "跳转的url", width = 15)
    @ApiModelProperty(value = "跳转的url")
    private java.lang.String url;
	/**参数*/
	@Excel(name = "参数", width = 15)
    @ApiModelProperty(value = "参数")
    private java.lang.String parm;
	/**图片地址*/
	@Excel(name = "图片地址", width = 15)
    @ApiModelProperty(value = "图片地址")
    private java.lang.String img;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
    private java.lang.Integer sort;
	/**创建人登录名称*/
    @ApiModelProperty(value = "创建人登录名称")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @Dict(dicCode = "del_flag")
    @ApiModelProperty(value = "删除标志")
    private java.lang.Integer delFlag;
    public static final String COL_DEL_FLAG = "del_flag";
}
