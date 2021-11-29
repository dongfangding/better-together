package com.ddf.better.together.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户上传的资源表 前端控制器
 * </p>
 * @menu 用户上传的资源表 前端控制器
 * @author mybatis-plus-generator
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/userResource")
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserResourceController {

    private final FastFileStorageClient fastFileStorageClient;


    /**
     * 测试上传
     *
     * @throws FileNotFoundException
     */
    @PostMapping("testUpload")
    public void upload(@RequestParam String filePath) throws FileNotFoundException {
        final File file = new File(filePath);
        String extName = filePath.substring(filePath.lastIndexOf(".") + 1);
        final StorePath png = fastFileStorageClient.uploadFile(new FileInputStream(file), file.length(), extName, null);
        log.info("文件上传成功， group = {}, path = {}, fullPath = {}", png.getGroup(), png.getPath(), png.getFullPath());
    }

}

