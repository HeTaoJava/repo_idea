package com.lagou.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CompressPic {
    public static Map addImages(MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断接收到的文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }
        //2.获取项目的部署路径
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));
        //3.获取文件名
        String originalFilename = file.getOriginalFilename();
        //4,生成新的文件名
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //5.文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);
        System.out.println(filePath);
        //如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录: " + filePath);
        }
        file.transferTo(filePath);
        //6.进行响应
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);
        return map;
    }
}
