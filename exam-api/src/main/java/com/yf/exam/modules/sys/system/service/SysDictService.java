package com.yf.exam.modules.sys.system.service;

/**
 * 数据字典工具类
 * @author bool
 */
public interface SysDictService {

    /**
     * 查找数据字典
     * @param table
     * @param text
     * @param key
     * @param value
     * @return
     */
    String findDict(String table,
                    String text,
                    String key,
                    String value);
}
