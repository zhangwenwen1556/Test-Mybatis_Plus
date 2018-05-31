package com.example.testmybatisplus.constants;


import java.lang.annotation.*;

/**
 * @author shouhan
 * @version 1.0
 * @date 2018/03/13
 * @desc 全局异常定义
 * @see
 * @since 1.0
 */
public class ExceptionConstants {


    /**
     * 错误信息注解
     */
    @Documented
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Error {

        //---------------------模块 begin----------------------
        /**
         * 模块 - 系统
         */
        String MODULE_USER = "USER";

        //-----------------------模块 end--------------------

        /**
         * @return 模块
         */
        String module();

        /**
         * @return 错误码
         */
        int code();

        /**
         * @return 信息
         */
        String msg();

    }

    // ------------------系统级 异常 begin-----------------------

    // ------------------user begin-----------------------
    @Error(module = Error.MODULE_USER, code = 100001, msg = "用不存在")
    public final static Integer USER_NOT_EXIST = 100001;

    @Error(module = Error.MODULE_USER, code = 100002, msg = "用户已经被删除")
    public final static Integer USER_IS_DELETE = 100002;

    @Error(module = Error.MODULE_USER, code = 100003, msg = "用户名称为空")
    public final static Integer USER_NAME_IS_NULL = 100003;

    @Error(module = Error.MODULE_USER, code = 100004, msg = "电话号码为空")
    public final static Integer USER_MOBILE_IS_NULL = 100004;

    @Error(module = Error.MODULE_USER, code = 100005, msg = "用户密码为空")
    public final static Integer USER_PASSWORD_IS_NULL = 100005;

    @Error(module = Error.MODULE_USER, code = 100006, msg = "职位为空")
    public final static Integer USER_POSITION_IS_NULL = 100006;

    @Error(module = Error.MODULE_USER, code = 100007, msg = "两次输入的密码不一致")
    public final static Integer DOUBLE_PASSWORD_DIFFERENT = 100007;

    @Error(module = Error.MODULE_USER, code = 100008, msg = "旧密码输入错误")
    public final static Integer OLD_PASSWORD_ENTRY_ERROR = 100008;
    // ------------------user end-----------------------



}