package com.ymx.fbms.service;

import com.github.pagehelper.PageInfo;
import com.ymx.fbms.pojo.Files;

import java.util.List;

/**
 * @author ymx
 * @since 2022-12-09
 */
public interface FilesService {
    /**
     * 获得所有文件分页数据
     * @param pageNum
     * @param pageSize
     * @return PageInfo<Files>
     */
    PageInfo<Files> getAllFiles(Integer pageNum, Integer pageSize);

    /**
     * 根据文件类型获取文件数据
     * @param pageNum
     * @param pageSize
     * @param fileType
     * @return PageInfo<Files>
     */
    PageInfo<Files> getFilePageByFileType(Integer pageNum, Integer pageSize, List<String> fileType);

    /**
     * 上传文件信息
     * @param files
     * @return int
     */
    int upLoadFiles(Files files);

    /**
     * 删除文件通过id
     * @param file
     * @return
     */
    int deleteFileById(Files file);

    /**
     * 通过id批量删除文件
     * @param ids
     * @return
     */
    int deleteFilesBatchByIds(List<Integer> ids);

    /**
     * 获得所有文件类型
     * @return List<String>
     */
    List<String> getAllFileType();

    /**
     * 通过文件对象修改文件的状态
     * @param file
     * @return int
     */
    int updateFileEnable(Files file);
}
