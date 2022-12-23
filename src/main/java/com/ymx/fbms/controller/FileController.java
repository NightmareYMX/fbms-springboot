package com.ymx.fbms.controller;

import com.github.pagehelper.PageInfo;
import com.ymx.fbms.pojo.Files;
import com.ymx.fbms.pojo.Result;
import com.ymx.fbms.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    FilesService filesService;

    @Value("${files.upload.profile}")
    private String profilePath;

    @Value("${files.upload.file}")
    String upLoadPath;

    @Value("${server.port}")
    private String serverPort;

    @Value("${server.address}")
    private String serverAddress;

    /**
     * 上传头像
     * @param file
     * @return Result
     */
    @PostMapping("/upLoadProfile")
    public Result upLoadProfile(@RequestParam MultipartFile file)  {
        if (file != null) {
            String fileName = null;
            try {
                String originalFilename = file.getOriginalFilename();
                String hzName = originalFilename.substring(originalFilename.lastIndexOf("."));
                fileName = UUID.randomUUID().toString() + hzName;
                File profile = new File(profilePath);
                if(!profile.exists()){
                    profile.mkdir();
                }
                String finalPath = profilePath + File.separator + fileName;
                file.transferTo(new File(finalPath));
            } catch (IOException | IllegalStateException e) {
                e.printStackTrace();
                return Result.error(Result.CODE_500, "保存文件失败");
            }
            String userProfile = "http://" + serverAddress + ":" + serverPort + "/profile/" + fileName;
            return Result.success(Result.CODE_200, userProfile);
        } else {
            return Result.error(Result.CODE_400, "上传文件失败");
        }
    }

    @PostMapping("/upLoad")
    public Result upLoad(@RequestParam MultipartFile file)  {
        if (file != null) {
            String fileName = null;
            try {
                String originalFilename = file.getOriginalFilename();
                String hzName = originalFilename.substring(originalFilename.lastIndexOf("."));
                String fileType = originalFilename.split("\\.")[1];
                fileName = UUID.randomUUID().toString() + hzName;
                File upLoadPathFile = new File(upLoadPath);
                if(!upLoadPathFile.exists()){
                    upLoadPathFile.mkdir();
                }
                String finalPath = upLoadPath + File.separator + fileName;
                String fileUrl = "http://" + serverAddress + ":" + serverPort + "/file/" + fileName;
                file.transferTo(new File(finalPath));
                Files files = new Files();
                files.setFileType(fileType);
                files.setFileName(fileName);
                files.setFileEnable(false);
                files.setFileSize(file.getSize());
                files.setFileUrl(fileUrl);
                int result = filesService.upLoadFiles(files);
                if (result == 1) {
                    return Result.success(Result.CODE_200, fileUrl);
                } else {
                    return Result.error(Result.CODE_500, "保存文件失败");
                }
            } catch (IOException | IllegalStateException e) {
                e.printStackTrace();
                return Result.error(Result.CODE_500, "保存文件失败");
            }
        } else {
            return Result.error(Result.CODE_400, "上传文件失败");
        }
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable String fileName) {
        ResponseEntity<byte[]> responseEntity = null;
        try {
            //创建输入流
            InputStream is = new FileInputStream(upLoadPath + File.separator + fileName);
            //创建字节数组
            byte[] bytes = new byte[is.available()];
            //将流读到字节数组中
            is.read(bytes);
            //创建HttpHeaders对象设置响应头信息
            MultiValueMap<String, String> headers = new HttpHeaders();
            //设置要下载方式以及下载文件的名字
            headers.add("Content-Disposition", String.format("attachment;filename=%s", fileName));
            //设置响应状态码
            HttpStatus statusCode = HttpStatus.OK;
            //创建ResponseEntity对象
            responseEntity = new ResponseEntity<>(bytes, headers,
                    statusCode);
            //关闭输入流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    /**
     * 根据文件类型展示所有文件信息分页
     * @param pageNum
     * @param pageSize
     * @param fileType
     * @return PageInfo<Files>
     */
    @GetMapping("/getFilePageByFileType")
    public PageInfo<Files> getFilePageByFileType(@RequestParam Integer pageNum,
                                                 @RequestParam Integer pageSize,
                                                 @RequestParam List<String> fileType
    ) {
        if (fileType.size() == 0) {
            return filesService.getAllFiles(pageNum, pageSize);
        } else {
            return filesService.getFilePageByFileType(pageNum, pageSize, fileType);
        }
    }

    /**
     * 通过id删除文件
     * @param file
     * @return Result
     */
    @PostMapping("/deleteFileById")
    public Result deleteFileById(@RequestBody Files file) {
        int result = filesService.deleteFileById(file);
        if (result == 1) {
            return Result.success(Result.CODE_200, result);
        } else {
            return Result.error(Result.CODE_500);
        }
    }

    /**
     * 通过id批量删除文件
     * @param ids
     * @return Result
     */
    @PostMapping("/deleteBatchByIds")
    public Result deleteBatchByIds(@RequestBody List<Integer> ids) {
        int result = filesService.deleteFilesBatchByIds(ids);
        if (result >= 1) {
            return Result.success(Result.CODE_200, result);
        } else {
            return Result.error(Result.CODE_500);
        }
    }

    /**
     * 得到所有的文件类型
     * @return
     */
    @GetMapping("/getAllFileType")
    public Result getAllFileType() {
        List<String> allFileType = filesService.getAllFileType();
        if (allFileType.isEmpty()) {
            return Result.error(Result.CODE_500, "查询失败");
        } else {
            return Result.success(Result.CODE_200, allFileType);
        }
    }

    @PostMapping("/updateFileEnable")
    public Result updateFileEnable(@RequestBody Files file) {
        int result = filesService.updateFileEnable(file);
        if (result == 1) {
            return Result.success(Result.CODE_200);
        } else {
            return Result.error(Result.CODE_500, "修改失败");
        }
    }
}
