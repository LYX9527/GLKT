package com.orange.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @Package : com.orange.vod.utils
 * @Author : yilantingfeng
 * @Date : 2022/7/3 2:43 PM
 * @Version : V1.0
 */
@Component
public class ConstantReadYml {
    public static String REGION;
    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String BUCKET_NAME;

    public String getREGION() {
        return REGION;
    }

    @Value("${tencent.cos.file.region}")
    public void setREGION(String REGION) {
        ConstantReadYml.REGION = REGION;
    }

    public String getSECRET_ID() {
        return SECRET_ID;
    }

    @Value("${tencent.cos.file.secretId}")
    public void setSECRET_ID(String SECRET_ID) {
        ConstantReadYml.SECRET_ID = SECRET_ID;
    }

    public String getSECRET_KEY() {
        return SECRET_KEY;
    }

    @Value("${tencent.cos.file.secretKey}")
    public void setSECRET_KEY(String SECRET_KEY) {
        ConstantReadYml.SECRET_KEY = SECRET_KEY;
    }

    public String getBUCKET_NAME() {
        return BUCKET_NAME;
    }

    @Value("${tencent.cos.file.bucketName}")
    public void setBUCKET_NAME(String BUCKET_NAME) {
        ConstantReadYml.BUCKET_NAME = BUCKET_NAME;
    }
}
