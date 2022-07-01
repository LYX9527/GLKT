package com.orange.vod.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : GLKT
 * @package : com.orange.vod.config
 * @className : VodConfig
 * @description:
 * @date : 2022/7/1 12:44
 */
@Configuration
@MapperScan("com.orange.vod.mapper")
public class VodConfig {
}
