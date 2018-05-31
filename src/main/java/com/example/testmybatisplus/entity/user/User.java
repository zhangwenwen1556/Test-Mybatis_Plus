package com.example.testmybatisplus.entity.user;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wenwen.zhang
 * @version 1.0
 * @date 18-5-31
 * @see
 * @since 1.0
 * @desc 用户
 */
@Data
@TableName("user")
public class User extends Model<User> {

    /**
     * 用户名称
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 员工姓名
     */
    @TableField("name")
    private String name;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 职位id
     */
    @TableField("position_id")
    private Integer positionId;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private Date createAt;

    /**
     * 被谁创建的
     */
    @TableField("create_user_id")
    private Date createUserId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
