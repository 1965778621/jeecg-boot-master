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
 * @Description: 参数配置
 * @Author: jeecg-boot
 * @Date:   2022-02-09
 * @Version: V1.0
 */
@Data
@TableName("cms_config")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cms_config对象", description="参数配置")
public class CmsConfig implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**参数键名*/
	@Excel(name = "参数键名", width = 15)
    @ApiModelProperty(value = "参数键名")
    private java.lang.String cmsKey;
	/**参数名称*/
	@Excel(name = "参数名称", width = 15)
    @ApiModelProperty(value = "参数名称")
    private java.lang.String name;
	/**参数键值*/
	@Excel(name = "参数键值", width = 15)
    @ApiModelProperty(value = "参数键值")
    private java.lang.String value;
	/**系统内置*/
	@Excel(name = "系统内置", width = 15, dicCode = "config_type")
	@Dict(dicCode = "config_type")
    @ApiModelProperty(value = "系统内置")
    private java.lang.Integer type;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**删除标志*/
	@Excel(name = "删除标志", width = 15, dicCode = "del_flag")
	@Dict(dicCode = "del_flag")
    @ApiModelProperty(value = "删除标志")
    private java.lang.Integer delFlag;
}
