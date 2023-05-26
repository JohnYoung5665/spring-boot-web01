package com.example.controller;

import com.example.pojo.Result;
import com.example.util.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    //存储本地文件
    /*@PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
        log.info("文件上传：{},{},{}",username,age,image);

        *//*
        * String getOriginalFilename();     获取原始文件名
        * void transferTo(File dest);       将接收的文件转存到磁盘文件中
        * long getSize();                   获取文件的大小，单位：字节
        * byte[] getBytes();                获取文件内容的字节数组
        * InputStream getInputStream();     获取接收到的文件内容的输入流
        * *//*
        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构造唯一的文件名(不能重复) - uuid(通用唯一识别码)
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名：{}",newFileName);

        //将文件存储在服务器的磁盘目录中 G:\images
        image.transferTo(new File("G:\\images\\" + newFileName));

        return Result.success();
    }*/

    @Autowired
    private AliOSSUtils aliOSSUtils;


    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名：{}",image.getOriginalFilename());
        //调用阿里云OSS工具类进行文件上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问的url：{}",url);

        return Result.success(url);
    }

}
