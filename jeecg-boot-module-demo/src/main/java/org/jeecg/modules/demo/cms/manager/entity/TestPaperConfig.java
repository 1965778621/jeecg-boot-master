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
 * @Description: 试卷配置
 * @Author: jeecg-boot
 * @Date:   2022-02-18
 * @Version: V1.0
 */
@Data
@TableName("cms_test_paper_config")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="test_paper_config对象", description="试卷配置")
public class TestPaperConfig implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**试卷名称*/
	@Excel(name = "试卷名称", width = 15)
    @ApiModelProperty(value = "试卷名称")
    private java.lang.String testName;
	/**考试时间*/
	@Excel(name = "考试时间", width = 15)
    @ApiModelProperty(value = "考试时间")
    private java.lang.Integer testTime;
	/**判断题数量*/
	@Excel(name = "判断题数量", width = 15)
    @ApiModelProperty(value = "判断题数量")
    private java.lang.Integer judgeTopicNum;
	/**判断题每题分数*/
	@Excel(name = "判断题每题分数", width = 15)
    @ApiModelProperty(value = "判断题每题分数")
    private java.lang.Integer judgeTopicScore;
	/**单选题题数量*/
	@Excel(name = "单选题题数量", width = 15)
    @ApiModelProperty(value = "单选题题数量")
    private java.lang.Integer singleChoiceNum;
	/**单选题每题分数*/
	@Excel(name = "单选题每题分数", width = 15)
    @ApiModelProperty(value = "单选题每题分数")
    private java.lang.Integer singleChoiceScore;
	/**多选题数量*/
	@Excel(name = "多选题数量", width = 15)
    @ApiModelProperty(value = "多选题数量")
    private java.lang.Integer multipleChoiceNum;
	/**多选题每题分数*/
	@Excel(name = "多选题每题分数", width = 15)
    @ApiModelProperty(value = "多选题每题分数")
    private java.lang.Integer multipleChoiceScore;
	/**总分*/
	@Excel(name = "总分", width = 15)
    @ApiModelProperty(value = "总分")
    private java.lang.Integer totalPoints;
	/**试卷描述*/
	@Excel(name = "试卷描述", width = 15)
    @ApiModelProperty(value = "试卷描述")
    private java.lang.String testDescribe;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
}
