package org.jeecg.modules.demo.im.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author henry(zhanghai)
 * @date 2022-02-10 21:54
 */
@ApiModel(value="文件记录")
@Data
@TableName(value = "im_file")
public class ImFile implements Serializable {
    /**
     * 文件id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="文件id")
    private Integer id;

    /**
     * 文件访问地址
     */
    @TableField(value = "url")
    @ApiModelProperty(value="文件访问地址")
    private String url;

    /**
     * 文件大小，单位字节
     */
    @TableField(value = "`size`")
    @ApiModelProperty(value="文件大小，单位字节")
    private Long size;

    /**
     * 文件名称
     */
    @TableField(value = "filename")
    @ApiModelProperty(value="文件名称")
    private String filename;

    /**
     * 原始文件名
     */
    @TableField(value = "original_filename")
    @ApiModelProperty(value="原始文件名")
    private String originalFilename;

    /**
     * 基础存储路径
     */
    @TableField(value = "base_path")
    @ApiModelProperty(value="基础存储路径")
    private String basePath;

    /**
     * 存储路径
     */
    @TableField(value = "`path`")
    @ApiModelProperty(value="存储路径")
    private String path;

    /**
     * 文件扩展名
     */
    @TableField(value = "ext")
    @ApiModelProperty(value="文件扩展名")
    private String ext;

    /**
     * 存储平台
     */
    @TableField(value = "platform")
    @ApiModelProperty(value="存储平台")
    private String platform;

    /**
     * 缩略图访问路径
     */
    @TableField(value = "th_url")
    @ApiModelProperty(value="缩略图访问路径")
    private String thUrl;

    /**
     * 缩略图名称
     */
    @TableField(value = "th_filename")
    @ApiModelProperty(value="缩略图名称")
    private String thFilename;

    /**
     * 缩略图大小，单位字节
     */
    @TableField(value = "th_size")
    @ApiModelProperty(value="缩略图大小，单位字节")
    private Long thSize;

    /**
     * 文件所属对象id
     */
    @TableField(value = "object_id")
    @ApiModelProperty(value="文件所属对象id")
    private String objectId;

    /**
     * 文件所属对象类型，例如用户头像，评价图片
     */
    @TableField(value = "object_type")
    @ApiModelProperty(value="文件所属对象类型，例如用户头像，评价图片")
    private String objectType;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_URL = "url";

    public static final String COL_SIZE = "size";

    public static final String COL_FILENAME = "filename";

    public static final String COL_ORIGINAL_FILENAME = "original_filename";

    public static final String COL_BASE_PATH = "base_path";

    public static final String COL_PATH = "path";

    public static final String COL_EXT = "ext";

    public static final String COL_PLATFORM = "platform";

    public static final String COL_TH_URL = "th_url";

    public static final String COL_TH_FILENAME = "th_filename";

    public static final String COL_TH_SIZE = "th_size";

    public static final String COL_OBJECT_ID = "object_id";

    public static final String COL_OBJECT_TYPE = "object_type";

    public static final String COL_CREATE_TIME = "create_time";
}
