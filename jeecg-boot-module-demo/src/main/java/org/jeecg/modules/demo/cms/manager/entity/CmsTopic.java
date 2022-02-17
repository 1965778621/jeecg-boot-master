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
 * @Description: 考试题目表
 * @Author: jeecg-boot
 * @Date:   2022-02-15
 * @Version: V1.0
 */
@Data
@TableName("cms_topic")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cms_topic对象", description="考试题目表")
public class CmsTopic implements Serializable {
    private static final long serialVersionUID = 1L;
	public static final String COL_DEL_FLAG = "del_flag";
	public static final String COL_VIDEO_CODE = "video_code";
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**视频编号ID*/
	@Excel(name = "视频编号ID", width = 15)
    @ApiModelProperty(value = "视频编号ID")
    private java.lang.String videoCode;
	/**题目类型*/
	@Excel(name = "题目类型", width = 15, dicCode = "topic_type")
	@Dict(dicCode = "topic_type")
    @ApiModelProperty(value = "题目类型")
    private java.lang.String topicType;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
    private java.lang.String fillNumber;
	/**题目标题*/
	@Excel(name = "题目标题", width = 15)
    @ApiModelProperty(value = "题目标题")
    private java.lang.Integer topicHeadline;
	/**正确答案*/
	@Excel(name = "正确答案", width = 15, dicCode = "answer_for")
	@Dict(dicCode = "answer_for")
    @ApiModelProperty(value = "正确答案")
    private java.lang.String topicAnswer;
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
	/**题目解析*/
	@Excel(name = "题目解析", width = 15)
    @ApiModelProperty(value = "题目解析")
    private java.lang.String answerRemark;
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
