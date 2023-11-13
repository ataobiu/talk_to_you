package com.example.server.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.server.common.AuthAccess;
import com.example.server.common.FileSaveUtil;
import com.example.server.response.Result;
import com.example.server.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: ataobiu
 * @Description: 文件上传下载接口
 * @DateTime: 2023/9/24 12:42
 **/
@RestController
@CrossOrigin
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    final UserService userService;
    final FileSaveUtil fileSaveUtil;

    //定义文件上传的根路径
    private final String ROOT_PATH = System.getProperty("user.home") + File.separator + ".config" + File.separator + "talkToYou" + File.separator + "files" + File.separator;


    // 使用 hutool 定义上传文件接口,实现文件上传
    @AuthAccess
    @PostMapping("/upload")
    public Result upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        val url = fileSaveUtil.uploadImage(file);
        return Result.success("上传成功", url);
    }

    @AuthAccess
    @GetMapping("/download/{file}")
    public void download(@PathVariable String file, HttpServletResponse res) throws IOException {
        OutputStream os;//新建一个输出流对象
        List<String> fileNames = FileUtil.listFileNames(ROOT_PATH);//获取所有的文件名称
        String fileName = fileNames.stream().filter(name -> name.contains(file)).findAny().orElse("");//找到跟参数一致的文件
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                res.addHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                res.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(ROOT_PATH + fileName);//通过文件的路径读取文件字节流
                os = res.getOutputStream();//通过response的输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }
}
