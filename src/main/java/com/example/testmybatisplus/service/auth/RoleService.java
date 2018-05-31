package com.example.testmybatisplus.service.auth;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.testmybatisplus.dao.auth.RoleMapper;
import com.example.testmybatisplus.entity.auth.Role;
import org.springframework.stereotype.Service;

/**
 * @author wenwen.zhang
 * @version 1.0
 * @date 18-5-31
 * @see
 * @since 1.0
 * @desc 权限
 */
@Service
public class RoleService  extends ServiceImpl<RoleMapper, Role> {

}
