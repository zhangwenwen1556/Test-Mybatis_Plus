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
 * @desc 权限
 */
@Data
@TableName("role")
public class Role extends Model<Role> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色名
     */
    @TableField("name")
    private String name;
    /**
     * 描述
     */
    @TableField("memo")
    private String memo;
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
     * 添加时间
     */
    @TableField("create_at")
    private Date createAt;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
