package com.example.demo.service;

import com.example.demo.entity.CommonResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
@Service
public class UpimageService {

    public CommonResult upimage(MultipartFile file, String savePath) {
        if (file == null) {
            return  new CommonResult(400,"请选择要上传的图片");
        }
        if (file.getSize() > 1024 * 1024 * 10) {
            return new CommonResult(400,"文件大小不能大于10M");
        }
        //获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            return new CommonResult(400,"请选择jpg,jpeg,gif,png格式的图片");
        }
//        String savePath = UPLOAD_FOLDER;
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        //通过UUID生成唯一文件名
        String filename = UUID.randomUUID().toString().replaceAll("-","") + "." + suffix;
        try {
            //将文件保存指定目录
            file.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400,"保存文件异常,可能是网络问题，请稍等再试");
        }
        //返回文件名称
        return new CommonResult(200,"上传成功","http://localhost:8080/img/"+filename);
    }
}
