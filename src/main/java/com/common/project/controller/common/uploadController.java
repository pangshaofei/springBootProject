package com.common.project.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.common.project.util.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class uploadController {

    @Value("${uploadStatic}")
    private String uploadStatic;

    /*
    * 上传文件到指定静态资源目录
    * */
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public JSONObject upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());

            if (!path.exists()) path = new File("");
            System.out.println("path:" + path.getAbsolutePath());

            //如果上传目录为/static/images/，则可以如下获取：
            File upload = new File(path.getAbsolutePath(), "static/images/");
            if (!upload.exists()) upload.mkdirs();
            System.out.println("upload url:" + upload.getAbsolutePath());
            Files.copy(file.getInputStream(), Paths.get(upload.getAbsolutePath(), file.getOriginalFilename()+ UUID.randomUUID()));
            return JsonUtil.setSuccessJson(uploadStatic+file.getOriginalFilename());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return JsonUtil.setFailJson("Fail");
    }
}


