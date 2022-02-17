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
 * @Description: 考试题库
 * @Author: jeecg-boot
 * @Date:   2022-02-13
 * @Version: V1.0
 */
@Data
@TableName("cms_topic")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cms_examination对象", description="考试题库")
public class CmsExamination implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**视频编号ID*/
	@Excel(name = "视频编号ID", width = 15)
    @ApiModelProperty(value = "视频编号ID")
    private java.lang.Integer courseCode;
	/**题目类型（多选2 单选1）*/
	@Excel(name = "题目类型（多选2 单选1）", width = 15)
    @ApiModelProperty(value = "题目类型（多选2 单选1）")
    private java.lang.Integer topicType;
	/**题目标题*/
	@Excel(name = "题目标题", width = 15)
    @ApiModelProperty(value = "题目标题")
    private java.lang.String questionText;
	/**题目正确答案*/
	@Excel(name = "题目正确答案", width = 15)
    @ApiModelProperty(value = "题目正确答案")
    private java.lang.String correctAnswer;
	/**选项A*/
	@Excel(name = "选项A", width = 15)
    @ApiModelProperty(value = "选项A")
    private java.lang.String answerForA;
	/**选项B*/
	@Excel(name = "选项B", width = 15)
    @ApiModelProperty(value = "选项B")
    private java.lang.String answerForB;
	/**选项C*/
	@Excel(name = "选项C", width = 15)
    @ApiModelProperty(value = "选项C")
    private java.lang.String answerForC;
	/**选项D*/
	@Excel(name = "选项D", width = 15)
    @ApiModelProperty(value = "选项D")
    private java.lang.String answerForD;
	/**选项分析*/
	@Excel(name = "选项分析", width = 15)
    @ApiModelProperty(value = "选项分析")
    private java.lang.String answerRemark;
	/**对应排列序号*/
	@Excel(name = "对应排列序号", width = 15)
    @ApiModelProperty(value = "对应排列序号")
    private java.lang.String fillNumber;
	/**删除标志 0未删除 1已删除*/
	@Excel(name = "删除标志 0未删除 1已删除", width = 15)
    @ApiModelProperty(value = "删除标志 0未删除 1已删除")
    private java.lang.Integer delFlag;
}
