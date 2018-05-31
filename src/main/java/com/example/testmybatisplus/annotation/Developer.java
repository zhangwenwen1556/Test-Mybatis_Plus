package com.example.testmybatisplus.annotation;

import java.lang.annotation.*;

/**
 * @author wenwen.zhang
 * @version 1.0
 * @date 18-5-31
 * @see
 * @since 1.0
 * @desc 方法注解，标记方法是谁做的(报错预警的时候给谁发信息)
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Developer {

    String name();
}
