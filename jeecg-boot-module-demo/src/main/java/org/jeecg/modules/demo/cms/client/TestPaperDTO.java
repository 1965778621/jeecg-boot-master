package org.jeecg.modules.demo.cms.client;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jeecg.modules.demo.cms.manager.entity.CmsTopic;

import java.util.List;

/**
 * \* @author: ZH
 * \* Date:    2022/2/18
 * \* Time:    15:51
 * \* Description:
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestPaperDTO {
    /**
     * judgeTopicList 判断题
     * singleChoiceList 单选题
     * multipleChoiceList 多选题
     */
    @ApiModelProperty(value = "总分")
    private Integer totalScore;

    @ApiModelProperty(value = "考试时间")
    private Integer testTime;

    @ApiModelProperty(value = "判断题")
    private List<CmsTopic> judgeTopicList;

    @ApiModelProperty(value = "判断题每题分数")
    private Integer judgeTopFraction;

    @ApiModelProperty(value = "单选题")
    private List<CmsTopic> singleChoiceList;

    @ApiModelProperty(value = "单选题每题分数")
    private Integer singleChoiceFraction;

    @ApiModelProperty(value = "多选题")
    private List<CmsTopic> multipleChoiceList;

    @ApiModelProperty(value = "多选题每题分数")
    private Integer multipleChoiceFraction;
}
