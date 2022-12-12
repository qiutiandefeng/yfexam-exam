package com.yf.exam.core.api;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 全局错误码定义，用于定义接口的响应数据，
 * 枚举名称全部使用代码命名，在系统中调用，免去取名难的问题。
 * @author bool 
 * @date 2019-06-14 21:15
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ApiError implements Serializable {


    /**
     * 通用错误，接口参数不全
     */
    ERROR_10010001("参数不全或类型错误！"),
    ERROR_10010002("您还未登录，请先登录！"),
    ERROR_10010003("数据不存在！"),
    ERROR_10010012("图形验证码错误！"),
    ERROR_10010013("短信验证码错误！"),
    ERROR_10010014("不允许重复评论！"),

    /**
     * 考试相关错误
     */
    ERROR_20010001("试题被删除，无法继续考试！"),
    ERROR_20010002("您有正在进行的考试！"),


    ERROR_90010001("账号不存在，请确认！"),
    ERROR_90010002("账号或密码错误！"),
    ERROR_90010003("至少要包含一个角色！"),
    ERROR_90010004("管理员账号无法修改！"),
    ERROR_90010005("账号被禁用，请联系管理员！"),
    ERROR_90010006("活动用户不足，无法开启竞拍！"),
    ERROR_90010007("旧密码不正确，请确认！"),


    ERROR_60000001("数据不存在！");

    public String msg;

    /**
     * 生成Markdown格式文档，用于更新文档用的
     * @param args
     */
    public static void main(String[] args) {
        for (ApiError e : ApiError.values()) {
            System.out.println("'"+e.name().replace("ERROR_", "")+"':'"+e.msg+"',");
        }
    }

    /**
     * 获取错误码
     * @return
     */
    public Integer getCode(){
        return Integer.parseInt(this.name().replace("ERROR_", ""));
    }
}
