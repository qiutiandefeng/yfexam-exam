package com.yf.exam.modules.sys.config.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
* <p>
* 通用配置实体类
* </p>
*
* @author 聪明笨狗
* @since 2020-04-17 09:12
*/
@Data
@TableName("sys_config")
public class SysConfig extends Model<SysConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 系统名称
     */
    @TableField("site_name")
    private String siteName;

    /**
     * 前端LOGO
     */
    @TableField("front_logo")
    private String frontLogo;

    /**
     * 后台LOGO
     */
    @TableField("back_logo")
    private String backLogo;

    /**
     * 版权信息
     */
    @TableField("copy_right")
    private String copyRight;
}
