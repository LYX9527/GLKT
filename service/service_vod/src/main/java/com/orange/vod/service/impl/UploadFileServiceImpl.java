package com.orange.vod.service.impl;

import com.orange.errorcode.ErrorCode;
import com.orange.exception.CustomException;
import com.orange.idUtils.UUID;
import com.orange.vod.service.UploadFileService;
import com.orange.vod.utils.ConstantReadYml;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;

/**
 * @Package : com.orange.vod.service.impl
 * @Author : yilantingfeng
 * @Date : 2022/7/3 2:38 PM
 * @Version : V1.0
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Override
    public HashMap<String, String> uploadFile(MultipartFile file) {
         String secretId = ConstantReadYml.SECRET_ID;
         String secretKey = ConstantReadYml.SECRET_KEY;
        // String region = ConstantReadYml.REGION;
        String regionStr = "ap-shanghai";
        // String bucketName = ConstantReadYml.BUCKET_NAME;
        String bucketName = "glkt-avatar-1252438937";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(regionStr);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cred, clientConfig);

        try {
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            String key = UUID.randomUUID() + filename;
            String s = new DateTime().toString("yyyy/MM/dd");
            String path = s + "/" + key;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, path, inputStream, objectMetadata);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            String url = "https://" + bucketName + ".cos." + regionStr + ".myqcloud.com/" + path;
            cosClient.shutdown();
            HashMap<String, String> resultMap = new HashMap<>();
            resultMap.put("imgUrl", url);
            resultMap.put("Key", path);
            return resultMap;
        } catch (Exception e) {
            throw new CustomException(ErrorCode.UPLOAD_FILE_FAIL, ErrorCode.UPLOAD_FILE_FAIL_MSG);
        }
    }

    @Override
    public void delFile(String key) {
        String secretId = "AKIDHboT5WkoJchWJDxTlrjOqfIjbMxMXWHo";
        String secretKey = "OaoprpcHm5D9tRlvxqZJsALJKEwSlz8B";
        String region = "ap-shanghai";
        String bucketName = "glkt-avatar-1252438937";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region1 = new Region(region);
        ClientConfig clientConfig = new ClientConfig(region1);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cred, clientConfig);
        cosClient.deleteObject(bucketName, key);
        cosClient.shutdown();
    }
}

