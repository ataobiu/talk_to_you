package com.example.server.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: ataobiu
 * @Description: TODO
 * @DateTime: 2023/11/14 1:18
 **/
@Service
public class FileSaveUtil {
    private final String ROOT_PATH = System.getProperty("user.home") + File.separator + ".config" + File.separator + "talkToYou" + File.separator + "files" + File.separator;
    @Value("${server-address}")
    private String address;
    @Value("${server.port}")
    private String port;

    public String uploadImage(MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        if (!FileUtil.exist(ROOT_PATH)) {
            FileUtil.mkdir(ROOT_PATH);  // 如果当前文件的父级目录不存在，就创建
        }
        // 用uuid替换文件名
        String fileName = null;
        if (originalFilename != null) {
            fileName = IdUtil.fastSimpleUUID() + getFileExtension(originalFilename);
        }
        String filePath = ROOT_PATH + fileName;
        FileUtil.writeBytes(image.getBytes(), filePath);
        // 返回文件下载地址
        return address + ":" + port + "/api/file/download/" + fileName; // 返回文件路径
    }

    // 获取文件扩展名
    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex);
        }
        return "";
    }
}
