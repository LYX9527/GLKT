package com.orange.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Package : com.orange.vod.utils
 * @Author : yilantingfeng
 * @Date : 2022/7/3 2:43 PM
 * @Version : V1.0
 */
public class ConstantReadYml {
    @Value("${tencent.cos.file.region}")
    public static String REGION;
    @Value("${tencent.cos.file.secretId}")
    public static String SECRET_ID;
    @Value("${tencent.cos.file.secretKey}")
    public static String SECRET_KEY;
    @Value("${tencent.cos.file.bucketName}")
    public static String BUCKET_NAME;

}
