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
 * @Description: 考试记录
 * @Author: jeecg-boot
 * @Date:   2022-02-15
 * @Version: V1.0
 */
@Data
@TableName("cms_exam_record")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cms_exam_record对象", description="考试记录")
public class CmsExamRecord implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**用户编号*/
	@Excel(name = "用户编号", width = 15)
    @ApiModelProperty(value = "用户编号")
    private java.lang.String userCode;
	/**视频编号*/
	@Excel(name = "视频编号", width = 15)
    @ApiModelProperty(value = "视频编号")
    private java.lang.Integer videoCode;
	/**题目编号*/
	@Excel(name = "题目编号", width = 15)
    @ApiModelProperty(value = "题目编号")
    private java.lang.String topicCode;
	/**用户选择*/
	@Excel(name = "用户选择", width = 15, dicCode = "answer_for")
	@Dict(dicCode = "answer_for")
    @ApiModelProperty(value = "用户选择")
    private java.lang.String userSelection;
	/**删除标志*/
	@Excel(name = "删除标志", width = 15, dicCode = "del_flag")
	@Dict(dicCode = "del_flag")
    @ApiModelProperty(value = "删除标志")
    private java.lang.Integer delFlag;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
}
