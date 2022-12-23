package com.ymx.fbms.mapper;

import com.ymx.fbms.pojo.Files;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilesMapper {
    List<Files> getAllFile();

    /**
     * 根据文件类型获取所有文件信息
     * @return List<Files>
     */
    List<Files> getFileByFileType(@Param("fileType") List<String> fileType);

    /**
     * 插入文件记录
     * @param file
     * @return int
     */
    int insert(@Param("file") Files file);

    /**
     * 通过id查找文件url
     * @param file
     * @return String
     */
    String selectUrlById(@Param("file") Files file);

    /**
     * 通过id批量查找文件url
     * @param ids
     * @return List<String>
     */
    List<String> selectUrlByIds(@Param("ids") List<Integer> ids);

    /**
     * 通过文件id删除文件
     * @param file
     * @return int
     */
    int deleteById(@Param("file") Files file);

    /**
     * 批量删除文件通过id
     * @param ids
     * @return int
     */
    int deleteBatchByIds(@Param("ids") List<Integer> ids);

    /**
     * 获得所有文件类型
     * @return List<String>
     */
    List<String> getAllFileTypes();

    /**
     * 通过文件id修改文件状态
     * @param fileId
     * @param fileEnable
     * @return int
     */
    int updateFileEnable(@Param("fileId") Integer fileId, @Param("fileEnable") Boolean fileEnable);
}
