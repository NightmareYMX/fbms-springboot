package com.ymx.fbms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymx.fbms.mapper.FilesMapper;
import com.ymx.fbms.pojo.Files;
import com.ymx.fbms.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${files.upload.file}")
    String upLoadPath;

    @Resource
    FilesMapper filesMapper;

    @Override
    public PageInfo<Files> getAllFiles(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(filesMapper.getAllFile());
    }

    @Override
    public PageInfo<Files> getFilePageByFileType(Integer pageNum, Integer pageSize, List<String> fileType) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(filesMapper.getFileByFileType(fileType));
    }

    @Override
    public int upLoadFiles(Files files) {
        return filesMapper.insert(files);
    }

    @Override
    public int deleteFileById(Files file) {
        String url = filesMapper.selectUrlById(file);
        String path = url.split("/")[4];
        try {
            java.nio.file.Files.delete(Paths.get(upLoadPath + File.separator + path));
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return filesMapper.deleteById(file);
    }

    @Override
    public int deleteFilesBatchByIds(List<Integer> ids) {
        List<String> urls = filesMapper.selectUrlByIds(ids);
        for (String url :
                urls) {
            String path = url.split("/")[4];
            try {
                java.nio.file.Files.delete(Paths.get(upLoadPath + File.separator + path));
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return filesMapper.deleteBatchByIds(ids);
    }

    @Override
    public List<String> getAllFileType() {
        return filesMapper.getAllFileTypes();
    }

    @Override
    public int updateFileEnable(Files file) {
        return filesMapper.updateFileEnable(file.getFileId(), file.getFileEnable());
    }
}
