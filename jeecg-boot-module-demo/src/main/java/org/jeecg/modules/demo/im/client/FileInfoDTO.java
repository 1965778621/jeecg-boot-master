package org.jeecg.modules.demo.im.client;

import cn.xuyanwu.spring.file.storage.FileInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author henry(zhanghai)
 * @date 2022-02-10 21:39
 */
@ApiModel(value="文件上传返回信息对象")
@Data
public class FileInfoDTO {
    /**
     * 文件访问地址
     */
    @ApiModelProperty(value = "文件访问路径，如果不以http://或https://开头则需要手动拼接访问地址，详细看附件文档")
    private String url;

    /**
     * 缩略图访问路径
     */
    @ApiModelProperty(value="缩略图访问路径，只有大尺寸图片和视频有缩略图的，小尺寸图片和其它文件不会有缩略图")
    private String thumbnailsUrl;

    private String server;

    public FileInfoDTO() { }
    public FileInfoDTO(FileInfo fileInfo) {
        this.url = fileInfo.getUrl();
        this.thumbnailsUrl = fileInfo.getThUrl();
    }


    public FileInfoDTO(FileInfo fileInfo,String server) {
        this.url = fileInfo.getUrl();
        this.thumbnailsUrl = fileInfo.getThUrl();
        this.server = server;
    }
}
