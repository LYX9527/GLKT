package com.orange.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * @Package : com.orange.vod.service
 * @Author : yilantingfeng
 * @Date : 2022/7/3 2:38 PM
 * @Version : V1.0
 */
public interface UploadFileService {
    HashMap<String,String> uploadFile(MultipartFile file);

    void delFile(String key);
}
