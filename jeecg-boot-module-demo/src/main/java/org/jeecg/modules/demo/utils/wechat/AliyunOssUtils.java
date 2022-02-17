package org.jeecg.modules.demo.utils.wechat;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.UUID;

/**
 * @author henry(zhanghai)
 * @date 2022-02-09 9:09
 */
@Slf4j
@Component
public class AliyunOssUtils {
    @Value("${jeecg.oss.endPoint}")
    private String endpoint;
    @Value("${jeecg.oss.accessKey}")
    private String accessKey;
    @Value("${jeecg.oss.secretKey}")
    private String secretKey;
    @Value("${jeecg.oss.bucketName}")
    private String bucketname;
    @Value("${jeecg.oss.filehost}")
    private String filehost;
    /**
     * 上传
     * @param file
     * @return
     */
    public  String upload(File file){
        log.info("=========>OSS文件上传开始："+file.getName());
        String accessKeyId= accessKey;
        String accessKeySecret=secretKey;
        String bucketName=bucketname;
        String fileHost=filehost;
        System.out.println(endpoint+"endpoint");
        if(null == file){
            return null;
        }
        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        try {
            //容器不存在，就创建
            if(! ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileUrl = fileHost+"/"+(UUID.randomUUID().toString().replace("-","")+"-"+file.getName());
            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            //设置权限 这里是公开读
            ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);
            if(null != result){
                log.info("==========>OSS文件上传成功,OSS地址："+fileUrl);
                return fileUrl;
            }
        } catch (OSSException oe) {
            log.error(oe.getMessage());
        } finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }
}
