package org.jeecg.modules.demo.im.manager.service;

import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.recorder.FileRecorder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.im.manager.entity.ImFile;
import org.jeecg.modules.demo.im.manager.mapper.ImFileMapper;
import org.jeecg.modules.demo.utils.wechat.Tools;
import org.springframework.stereotype.Service;

/**
 * @author henry(zhanghai)
 * @date 2022-02-10 22:00
 */
@Service
public class ImFileService extends ServiceImpl<ImFileMapper, ImFile> implements FileRecorder {

    /**
     * 保存文件上传记录
     */
    @Override
    public boolean record(FileInfo fileInfo) {
        ImFile imFile = Tools.copyProperties(fileInfo,ImFile.class);
        return save(imFile);
    }

    /**
     * 根据 url 获取文件记录
     */
    @Override
    public FileInfo getByUrl(String url) {
        ImFile imFile = getOne(new LambdaQueryWrapper<ImFile>().eq(ImFile::getUrl,url));
        return Tools.copyProperties(imFile,FileInfo.class);
    }

    /**
     * 根据 url 删除文件记录
     */
    @Override
    public boolean delete(String url) {
        return remove(new LambdaQueryWrapper<ImFile>().eq(ImFile::getUrl,url));
    }
}
