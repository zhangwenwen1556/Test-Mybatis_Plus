package com.example.testmybatisplus.rest.user;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.testmybatisplus.annotation.Developer;
import com.example.testmybatisplus.constants.Constants;
import com.example.testmybatisplus.entity.user.User;
import com.example.testmybatisplus.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenwen.zhang
 * @version 1.0
 * @date 18-5-31
 * @desc 用户
 * @see
 * @since 1.0
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     *
     * @param user 用户实体
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void insert(User user) {
        userService.insertUser(user);
    }

    /**
     * 修改用户
     *
     * @param user 用户实体
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(User user) {
        userService.updateUser(user);
    }

    /**
     * 用户明细
     *
     * @param id 用户ID
     * @return
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public User detail(@RequestParam("id") Long id) {
        return userService.findByIdValid(id);
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(@RequestParam("id") Long id) {
        userService.deleteUser(id);
    }

    /**
     * 用户分页查询
     * @param page 查询的page
     * @return
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public Page<User> page(Page<User> page){
        return userService.page(page);
    }

    /**
     * 修改密码
     * @param id 用户ID
     * @param oldPwd 旧密码
     * @param newPwd 密码
     * @param newPwdConfirm 新密码确认
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    @RequestMapping(value = "update-pwd", method = RequestMethod.GET)
    public void updatePassword(@RequestParam("id") Long id,
                               @RequestParam("oldPwd") String oldPwd,
                               @RequestParam("newPwd") String newPwd,
                               @RequestParam("newPwdConfirm") String newPwdConfirm) {
        userService.updatePassword(id, oldPwd, newPwd, newPwdConfirm);
    }
}
