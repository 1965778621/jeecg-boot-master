package org.jeecg.modules.demo.im.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Description: im_user
 * @Author: jeecg-boot
 * @Date: 2022-02-08
 * @Version: V1.0
 */
@Data
@TableName("im_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "im_user对象", description = "im_user")
public class ImUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * 小程序昵称
     */
    @Excel(name = "小程序昵称", width = 15)
    @ApiModelProperty(value = "小程序昵称")
    private String appletNickName;
    /**
     * 小程序头像
     */
    @Excel(name = "小程序头像", width = 15)
    @ApiModelProperty(value = "小程序头像")
    private String avatarUrl;
    /**
     * unionid小程序和公众号关联字段
     */
    @Excel(name = "unionid小程序和公众号关联字段", width = 15)
    @ApiModelProperty(value = "unionid小程序和公众号关联字段")
    private String unionid;
    /**
     * 小程序openid
     */
    @Excel(name = "小程序openid", width = 15)
    @ApiModelProperty(value = "小程序openid")
    private String appletOpenid;
    /**
     * 小程序session_key
     */
    @Excel(name = "小程序session_key", width = 15)
    @ApiModelProperty(value = "小程序session_key")
    private String sessionKey;
    /**
     * 小程序手机号码
     */
    @Excel(name = "小程序手机号码", width = 15)
    @ApiModelProperty(value = "小程序手机号码")
    private String miniTel;
    /**
     * 公众号昵称
     */
    @Excel(name = "公众号昵称", width = 15)
    @ApiModelProperty(value = "公众号昵称")
    private String nickNameMphelper;
    /**
     * 公众号头像
     */
    @Excel(name = "公众号头像", width = 15)
    @ApiModelProperty(value = "公众号头像")
    private String headimgurl;
    /**
     * 公众号openid
     */
    @Excel(name = "公众号openid", width = 15)
    @ApiModelProperty(value = "公众号openid")
    private String openidMphelper;
    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名", width = 15)
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    /**
     * 手机号码
     */
    @Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
    private String tel;
    /**
     * 公司名称
     */
    @Excel(name = "公司名称", width = 15)
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    /**
     * 公司职位
     */
    @Excel(name = "公司职位", width = 15)
    @ApiModelProperty(value = "公司职位")
    private String companyPosition;
    /**
     * 证件照
     */
    @Excel(name = "证件照", width = 15)
    @ApiModelProperty(value = "证件照")
    private String identificationPhoto;
    /**
     * 公司税务号
     */
    @Excel(name = "公司税务号", width = 15)
    @ApiModelProperty(value = "公司税务号")
    private String companyNo;
    /**
     * 资料更新
     */
    @Excel(name = "资料更新", width = 15, dicCode = "imUser_status")
    @Dict(dicCode = "imUser_status")
    @ApiModelProperty(value = "资料更新")
    private Integer status;
    /**
     * 删除标识
     */
    @Excel(name = "删除标识", width = 15, dicCode = "del_flag")
    @Dict(dicCode = "del_flag")
    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;
    /**
     * 考试成绩
     */
    @Excel(name = "考试成绩", width = 15, dicCode = "exam_results")
    @Dict(dicCode = "exam_results")
    @ApiModelProperty(value = "考试成绩")
    private Integer examResults;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    @Dict(dicCode = "create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private Date updTime;
    /**
     * token
     */
    @Excel(name = "token", width = 15)
    @ApiModelProperty(value = "token")
    private String token;
    /**
     * token过期时间
     */
    @Excel(name = "token过期时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "token过期时间")
    private Date tokenTime;
    /**
     * 证书开始时间
     */
    @Excel(name = "证书开始时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "证书开始时间")
    private LocalDate certificateStartTime;
    /**
     * 证书结束时间
     */
    @Excel(name = "证书结束时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "证书结束时间")
    private LocalDate certificateEndTime;

    @Excel(name = "考试标识", width = 15, dicCode = "pass_status")
    @Dict(dicCode = "pass_status")
    @ApiModelProperty(value = "考试标识")
    private Integer passStatus;

}
