package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for creating an instance of AliOssUtil
 */
@Configuration
@Slf4j
public class OssConfiguration {
    /**
     * create an instance of AliOssUtil
     * @param aliOssProperties
     * @return
     */
    @Bean
    @ConditionalOnMissingBean // make sure there's only one instance of AliOssUtil in the server
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
        log.info("creating an instance of AliOssUtil:{}", aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName());
    }
}
