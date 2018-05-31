package com.example.testmybatisplus.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("log_server_exception_log")
public class ServerExceptionLog extends Model<ServerExceptionLog> {

    /**
     * 处理状态 - 已处理
     */
    public static int STATUS_YES = 1;
    /**
     * 处理状态 - 未处理
     */
    public static int STATUS_NO = -1;

    /**
     * 邮件发送状态 - 未发送
     */
    public static Integer SEND_MAIL_NO = 1;
    /**
     * 邮件发送状态 - 已发送
     */
    public static Integer SEND_MAIL_SUCCESS = 2;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 异常
     */
    private String exception;

    /**
     * 用户类型
     */
    @TableField("user_type")
    private Integer userType;
    /**
     * 用户编号
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 系统类型
     */
    private Integer sys;
    /**
     * 请求地址
     */
    @TableField("request_uri")
    private String requestUri;
    /**
     * 请求方法
     */
    @TableField("request_method")
    private String requestMethod;
    /**
     * 请求参数
     */
    @TableField("request_params")
    private String requestParams;
    /**
     * UA
     */
    private String ua;
    /**
     * IP
     */
    private String ip;
    /**
     * 记录时间
     */
    private Date createAt;
    /**
     * 处理状态
     */
    private Integer status;

    /**
     * 备注
     */
    @TableField("note")
    private String note;

    /**
     * 解决时间
     */
    private Date solveAt;

    /**
     * 邮件发送状态
     */
    private Integer sendMailStatus;
    /**
     * 邮件发送时间
     */
    private Date sendMailTime;
    /**
     * 发送到第几级别了
     */
    private Integer sendLevel;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}