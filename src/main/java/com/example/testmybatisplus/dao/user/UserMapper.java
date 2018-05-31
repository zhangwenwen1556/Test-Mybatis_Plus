package com.example.testmybatisplus.dao.user;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.testmybatisplus.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wenwen.zhang
 * @version 1.0
 * @date 18-5-31
 * @see
 * @since 1.0
 * @desc 用户mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
