package com.example.testmybatisplus.dao.auth;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.testmybatisplus.entity.auth.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wenwen.zhang
 * @version 1.0
 * @date 18-5-31
 * @see
 * @since 1.0
 * @desc 权限
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
