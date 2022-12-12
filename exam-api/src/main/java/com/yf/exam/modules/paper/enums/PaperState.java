package com.yf.exam.modules.paper.enums;


/**
 * 试卷状态
 * @author bool 
 * @date 2019-10-30 13:11
 */
public interface PaperState {


    /**
     * 考试中
     */
    Integer ING = 0;

    /**
     * 待阅卷
     */
    Integer WAIT_OPT = 1;

    /**
     * 已完成
     */
    Integer FINISHED = 2;

    /**
     * 弃考
     */
    Integer BREAK = 3;

    
}
