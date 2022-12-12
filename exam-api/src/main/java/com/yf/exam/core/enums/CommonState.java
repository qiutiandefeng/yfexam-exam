package com.yf.exam.core.enums;

/**
 * 通用的状态枚举信息
 *
 * @author bool
 * @date 2019-09-17 17:57
 */
public interface CommonState {

    /**
     * 普通状态，正常的
     */
    Integer NORMAL = 0;
    /**
     * 非正常状态，禁用，下架等
     */
    Integer ABNORMAL = 1;
}
