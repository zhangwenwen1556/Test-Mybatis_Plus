package com.example.testmybatisplus.entity.auth;

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
 * @desc 用户权限
 */
@Data
@TableName("user_role")
public class UserRole extends Model<UserRole> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 员工ID
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 状态
     */
    @TableField("status")
    private Integer status;
    /**
     * 创建人
     */
    @TableField("create_user_id")
    private Integer createUserId;
    /**
     * 创建时间
     */
    @TableField("create_at")
    private Date createAt;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
