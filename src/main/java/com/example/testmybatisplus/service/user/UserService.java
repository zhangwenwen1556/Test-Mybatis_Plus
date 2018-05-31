package com.example.testmybatisplus.service.user;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.testmybatisplus.annotation.Developer;
import com.example.testmybatisplus.constants.Constants;
import com.example.testmybatisplus.constants.ExceptionConstants;
import com.example.testmybatisplus.dao.user.UserMapper;
import com.example.testmybatisplus.entity.user.User;
import com.example.testmybatisplus.service.exception.ServiceExceptionService;
import com.example.testmybatisplus.utils.CollectionUtil;
import com.example.testmybatisplus.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Autowired
    private ServiceExceptionService serviceExceptionService;

    /**
     * 添加用户
     *
     * @param user 用户实体
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    public void insertUser(User user) {
        // 验证用户信息是否填写完成
        validationUser(user);
        String pwd = user.getPassword();
        user.setPassword(EncryptUtil.md5Encrypt(EncryptUtil.ENCRYPT_CONSTENT + pwd + EncryptUtil.ENCRYPT_CONSTENT));
        this.baseMapper.insert(user);
    }

    /**
     * 修改用户
     *
     * @param user 用户实体
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    public void updateUser(User user) {
        // 验证用户信息是否填写完成
        validationUser(user);
        user.setPassword(null);
        if (user.getId() == null) {
            // TODO 抛出异常：用户ID不能为空
        }
        this.updateById(user);
    }

    /**
     * 根据ID查询用户(所有状态的用户)
     *
     * @param id
     * @return
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    public User findById(Long id) {
        User user = this.selectById(id);
        return user;
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    public void deleteUser(Long id) {
        // 查询一下，验证用户是否存在
        User user = this.findByIdValid(id);
        User updateUser = new User();
        updateUser.setId(id);
        updateUser.setStatus(Constants.STATUS_INVALID);
        this.updateById(updateUser);
    }

    /**
     * 根据ID查询用户(只查询正常状态的用户)
     *
     * @param id 用户ID
     * @return
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    public User findByIdValid(Long id) {
        User user = this.findById(id);
        if (user == null) {
            // 用户不存在
            throw serviceExceptionService.createServiceException(ExceptionConstants.USER_NOT_EXIST);
        } else if (Constants.STATUS_INVALID.equals(user.getStatus())) {
            // 用户已被删除
            throw serviceExceptionService.createServiceException(ExceptionConstants.USER_IS_DELETE);
        }
        return user;
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    public Page<User> page(Page<User> page) {
        page.setDescs(CollectionUtil.asArrayList("create_at"));
        selectPage(page);
        return page;
    }

    /**
     * 修改密码
     * @param id 用户ID
     * @param oldPwd 旧密码
     * @param newPwd 密码
     * @param newPwdConfirm 新密码确认
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    public void updatePassword(Long id, String oldPwd, String newPwd, String newPwdConfirm) {
        if (!newPwd.equals(newPwdConfirm)) {
            // 两次输入的密码不正确
            throw serviceExceptionService.createServiceException(ExceptionConstants.DOUBLE_PASSWORD_DIFFERENT);
        }
        User user = this.findByIdValid(id);
        // 对旧密码进行加密和数据库中的密码进行比较，如果不一样就说明密码输入错误
        {
            // TODO 不确定后台修改密码是否需要输入旧密码，所有这里这么写，如果不需要输入旧密码的话，直接删除掉
            String oldPwdEncrypt = EncryptUtil.md5Encrypt(EncryptUtil.ENCRYPT_CONSTENT + oldPwd + EncryptUtil.ENCRYPT_CONSTENT);
            if (!oldPwdEncrypt.equals(user.getPassword())) {
                // 旧密码输入错误
                throw serviceExceptionService.createServiceException(ExceptionConstants.OLD_PASSWORD_ENTRY_ERROR);
            }
        }
        User updateUser = new User();
        updateUser.setPassword(EncryptUtil.md5Encrypt(EncryptUtil.ENCRYPT_CONSTENT + newPwd + EncryptUtil.ENCRYPT_CONSTENT));
        updateUser.setId(id);
        this.updateById(updateUser);
    }

    /**
     * 验证user的一些必填字段
     *
     * @param user 用户实体
     */
    @Developer(name = Constants.DEVELOPER_NAME_ZHANGWENWEN)
    private void validationUser(User user) {
        if (StringUtils.isEmpty(user.getName())) {
            // 名称为空
            throw serviceExceptionService.createServiceException(ExceptionConstants.USER_NAME_IS_NULL);
        }
        if (StringUtils.isEmpty(user.getMobile())) {
            // 电话号码为空
            throw serviceExceptionService.createServiceException(ExceptionConstants.USER_MOBILE_IS_NULL);
        }
        if (StringUtils.isEmpty(user.getPassword()) && user.getId() == null) {
            // 密码为空
            throw serviceExceptionService.createServiceException(ExceptionConstants.USER_PASSWORD_IS_NULL);
        }
        if (StringUtils.isEmpty(user.getPositionId())) {
            // 职位为空
            throw serviceExceptionService.createServiceException(ExceptionConstants.USER_POSITION_IS_NULL);
        }
    }

}
