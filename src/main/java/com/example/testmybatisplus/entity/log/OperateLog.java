package com.example.testmybatisplus.entity.log;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * @author wenwen.zhang
 * @version 1.0
 * @date 18-5-31
 * @see
 * @since 1.0
 * @desc 平台操作日志
 */
@Data
@TableName("log_operate_log")
public class OperateLog {

    /**
     * 主编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 操作用户编号
     */
    @TableField("user_id")
    private Long userId;
    /**
     * ip地址
     */
    @TableField("ip")
    private String ip;

    /**
     * 模块类型
     */
    @TableField("module_type")
    private Integer moduleType;
    /**
     * 操作类型
     */
    @TableField("operate_type")
    private Integer operateType;
    /**
     * 添加时间
     */
    @TableField("add_time")
    private Date addTime;
    /**
     * 操作内容
     */
    @TableField("content")
    private String content;
    /**
     * 存储主数据编号
     */
    @TableField("business_ids")
    private String businessIds;
    /**
     * 扩展参数
     */
    @TableField("extra_map")
    private String extraMap;
    /**
     * UA
     */
    @TableField("ua")
    private String ua;

    public OperateLog() {
    }
}
