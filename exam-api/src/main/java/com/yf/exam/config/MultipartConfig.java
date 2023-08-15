package com.yf.exam.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * 文件上传配置
 * @author bool
 * @date 2019-07-29 16:23
 */
@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(DataSize.ofMegabytes(5000L));
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(5000L));
        return factory.createMultipartConfig();
    }

}
