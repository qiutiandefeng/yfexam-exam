package com.yf.exam.ability.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * 文件上传配置
 * @author van
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "conf.upload")
public class UploadConfig {

    /**
     * 访问路径
     */
    private String url;

    /**
     * 物理目录
     */
    private String dir;

    /**
     * 允许的后缀
     */
    private String [] allowExtensions;

}
