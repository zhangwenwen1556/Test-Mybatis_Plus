package com.example.testmybatisplus.service.exception;

import com.example.testmybatisplus.constants.ExceptionConstants;
import com.example.testmybatisplus.exception.ServiceException;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wenwen.zhang
 * @version 1.0
 * @date 18-5-31
 * @see
 * @since 1.0
 * @desc 异常抛出
 */
@Service
public class ServiceExceptionService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionService.class);
    private static LinkedHashMap<Integer, String> msgMap;

    /**
     * 返回业务异常
     *
     * @param code 错误码
     */

    public ServiceException createServiceException(int code) {
        return new ServiceException(code, getMsg(code));
    }

    /**
     * 返回业务异常
     *
     * @param code 错误码
     * @param msg  错误信息
     */
    @Deprecated
    public ServiceException createServiceException(int code, String msg) {
        return new ServiceException(code, msg);
    }

    /**
     * 返回业务异常
     *
     * 将错误编号对应的消息使用params进行格式化。
     *
     * 具体规则为：我有{}只小毛驴，我重来也不{}.这里拓展参数可以使用{1, "打"}。
     *
     * @param code   错误码
     * @param params 消息格式化参数
     */
    public ServiceException createServiceException(int code, Object... params) {
        String msg = getMsg(code);
        // 格式化 参数
        if (!StringUtils.isEmpty(msg)) {
            msg = format(code, msg, params);
        } else {
            LOGGER.error("错误码: {} 未配置错误信息！", code);
            msg = "";
        }
        return new ServiceException(code, msg);
    }

    /**
     * 返回业务异常
     *
     * @param code   错误码
     * @param params 消息格式化参数
     */
    @Deprecated
    public ServiceException createServiceExceptionWithParams(int code, Map<String, Object> extParams, Object... params) {
        return null;
    }


    /**
     * 获取错误码对应的错误信息
     */
    private static String getMsg(Integer code) {
        Preconditions.checkNotNull(msgMap, "错误码未初始化");
        if (msgMap.containsKey(code)) {
            return msgMap.get(code);
        } else {
            LOGGER.error("错误码: {} 未配置错误信息！", code);
            return "";
        }
    }

    /**
     * 初始化 - 错误码信息
     */
    @PostConstruct
    public synchronized void init() {
        long now = System.currentTimeMillis();
        LOGGER.info("初始化 ExceptionConstants 错误码信息 开始...");
        LinkedHashMap<Integer, String> tempMsgMap = new LinkedHashMap<Integer, String>();
        ReflectionUtils.doWithFields(ExceptionConstants.class, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException {
                // 没有 @Error 注解的， 不做处理
                if (field.getAnnotation(ExceptionConstants.Error.class) == null) {
                    return;
                }
                Map<String, Object> row = parseField(field);
                // 重复的code码校验
                if (row.containsKey("code")) {
                    if (tempMsgMap.containsKey(row.get("code"))) {
                        LOGGER.error("错误码重复: {} 的code {} 重复！", field.getName(), row.get("code"));
                    }
                }
                tempMsgMap.put((Integer) row.get("code"), row.get("msg").toString());
            }
        });

        msgMap = tempMsgMap;

        LOGGER.info("初始化 ExceptionConstants 错误码信息完成...消耗：{}毫秒!", System.currentTimeMillis() - now);
    }


    /**
     * 解析 Field
     *
     * @param field 常量
     */
    private Map<String, Object> parseField(Field field) {
        if (field.isAccessible()) {
            field.setAccessible(true);
        }
        // 类型校验
        if (field.getType() == int.class || field.getType() == Integer.class) {
            // 无异常
        } else {
            throw new RuntimeException("错误码: " + field.getName() + " 类型错误，必须为int/Integer类型数据！");
        }

        Map<String, Object> row = new HashMap<>();
        row.put("name", field.getName()); // 获取常量名称

        ExceptionConstants.Error error = field.getAnnotation(ExceptionConstants.Error.class);
        row.put("module", error.module());
        row.put("code", error.code());
        row.put("msg", error.msg());
        return row;
    }


    /**
     * 将错误编号对应的消息使用params进行格式化。
     *
     * 具体规则为：我有{}只小毛驴，我重来也不{}.这里拓展参数可以使用{1, "打"}。
     *
     * @param code   错误编号
     * @param msg    消息模版
     * @param params 参数
     *
     * @return 格式化后的提示
     */
    private String format(int code, String msg, Object... params) {
        StringBuilder sbuf = new StringBuilder(msg.length() + 50);
        int i = 0;
        int j;
        int l;
        for (l = 0; l < params.length; l++) {
            j = msg.indexOf("{}", i);
            if (j == -1) {
                LOGGER.error("[format][参数过多：错误码({})|错误内容({})|参数({})", code, msg, params);
                if (i == 0) {
                    return msg;
                } else {
                    sbuf.append(msg.substring(i, msg.length()));
                    return sbuf.toString();
                }
            } else {
                sbuf.append(msg.substring(i, j));
                sbuf.append(params[l]);
                i = j + 2;
            }
        }
        if (msg.indexOf("{}", i) != -1) {
            LOGGER.error("[format][参数过少：错误码({})|错误内容({})|参数({})", code, msg, params);
        }
        sbuf.append(msg.substring(i, msg.length()));
        return sbuf.toString();
    }
}