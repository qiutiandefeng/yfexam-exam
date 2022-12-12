package com.yf.exam.core.api.utils;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON数据转换器，用于转换返回消息的格式
 * @author dav
 * @date 2018/9/11 19:30
 */
public class JsonConverter {

    /**
     * FastJson消息转换器
     *
     * @return
     */
    public static HttpMessageConverter fastConverter() {
        // 定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 添加FastJson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 默认转换器
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.MapSortField,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullListAsEmpty);
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        // 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        // 在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        return fastConverter;
    }
}
