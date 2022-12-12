package com.yf.exam.modules.sys.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* <p>
* 机主信息Mapper
* </p>
*
* @author 聪明笨狗
* @since 2020-08-22 13:46
*/
@Mapper
public interface SysDictMapper {

    /**
     * 查找数据字典
     * @param table
     * @param text
     * @param key
     * @param value
     * @return
     */
    String findDict(@Param("table") String table,
                    @Param("text") String text,
                    @Param("key") String key,
                    @Param("value") String value);
}
