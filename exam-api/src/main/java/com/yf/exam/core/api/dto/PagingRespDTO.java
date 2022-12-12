package com.yf.exam.core.api.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 分页响应类
 * @author bool 
 * @date 2019-07-20 15:17
 * @param <T>
 */
public class PagingRespDTO<T> extends Page<T> {

    /**
     * 获取页面总数量
     * @return
     */
    @Override
    public long getPages() {
        if (this.getSize() == 0L) {
            return 0L;
        } else {
            long pages = this.getTotal() / this.getSize();
            if (this.getTotal() % this.getSize() != 0L) {
                ++pages;
            }
            return pages;
        }
    }

}
