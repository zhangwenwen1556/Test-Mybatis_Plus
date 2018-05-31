package com.example.testmybatisplus.entity.wx;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.example.testmybatisplus.constants.Constants;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenwen.zhang
 * @version 1.0
 * @date 2018/5/31
 * @see
 * @since 1.0
 * @desc 微信消息实体
 */
@Data
@TableName("wx_message")
public class WXMessage extends Model<WXMessage> {

    /**
     * 消息类型中文字符对照
     */
    public static final Map<String, String> MSG_TYPE = new HashMap<String, String>(){{
        put(Constants.MSG_TYPE_TEXT, Constants.MSG_TYPE_TEXT_NAME);
        put(Constants.MSG_TYPE_IMAGE, Constants.MSG_TYPE_IMAGE_NAME);
        put(Constants.MSG_TYPE_VOICE, Constants.MSG_TYPE_VOICE_NAME);
        put(Constants.MSG_TYPE_VIDEO, Constants.MSG_TYPE_VIDEO_NAME);
        put(Constants.MSG_TYPE_SHORT_VIDEO, Constants.MSG_TYPE_SHORT_VIDEO_NAME);
        put(Constants.MSG_TYPE_LOCATION, Constants.MSG_TYPE_LOCATION_NAME);
        put(Constants.MSG_TYPE_LINK, Constants.MSG_TYPE_LINK_NAME);
    }};

    /**
     * 系统中的ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 该条记录在本系统中被创建的时间
     */
    @TableField("sys_time")
    private Date sysTime;

    /**
     * 开发者微信号
     */
    @TableField("to_user_name")
    private Date toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    @TableField("from_user_name")
    private Date fromUserName;

    /**
     * 该条记录在维系系统被创建的时间
     */
    @TableField("create_time")
    private Long createTime;
    @TableField("create_at")
    private Long createAt;

    /**
     * 消息类型 (text/image/voice/amr/video/shortvideo/location/link)
     *
     * {@link Constants#MSG_TYPE_TEXT} 文本
     * {@link Constants#MSG_TYPE_IMAGE} 图片
     * {@link Constants#MSG_TYPE_VOICE} 语音
     * {@link Constants#MSG_TYPE_VIDEO} 视频
     * {@link Constants#MSG_TYPE_SHORT_VIDEO} 短视频
     * {@link Constants#MSG_TYPE_LOCATION} 地址
     * {@link Constants#MSG_TYPE_LINK} 连接
     * {@link this#MSG_TYPE} 字符文本对照
     *
     */
    @TableField("msg_type")
    private Long msgType;
    // --------------------文本消息begin--------------------
    /**
     * 文本消息内容
     */
    @TableField("content")
    private String content;
    // --------------------文本消息end--------------------

    // --------------------图片消息begin--------------------
    /**
     * 图片链接（由系统生成）
     */
    @TableField("pic_url")
    private String picUrl;
    // --------------------图片消息end--------------------

    // --------------------语音消息begin--------------------
    /**
     * 语音消息格式 例如：amr,speex等
     */
    @TableField("format")
    private String format;

    /**
     * 微信语音识别后的结果
     */
    @TableField("recognition")
    private String recognition;
    // --------------------语音消息end--------------------

    // --------------------视频消息/小视频消息begin--------------------
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
     */
    @TableField("thumb_media_id")
    private String thumbMediaId;
    // --------------------视频消息/小视频消息end--------------------

    // --------------------地里位置消息begin--------------------
    /**
     * 地理位置维度
     */
    @TableField("location_x")
    private String locationX;

    /**
     * 地理位置经度
     */
    @TableField("location_y")
    private String locationY;

    /**
     * 地图缩放大小
     */
    @TableField("scale")
    private Integer scale;

    /**
     * 位置信息
     */
    @TableField("label")
    private String label;
    // --------------------地理位置消息end--------------------

    // --------------------链接消息begin--------------------
    /**
     * 位置信息
     */
    @TableField("title")
    private String title;

    /**
     * 消息描述
     */
    @TableField("description")
    private String description;

    /**
     * 消息链接
     */
    @TableField("url")
    private String url;
    // --------------------链接消息end--------------------

    /**
     * 图片链接（由系统生成）
     */
    @TableField("media_id")
    private String mediaId;

    /**
     * 消息在微信中的ID
     */
    @TableField("msg_id")
    private String msgId;

    /**
     * 系统状态
     * {@link Constants#STATUS_INVALID}
     * {@link Constants#STATUS_VALID}
     */
    @TableField("status")
    private Integer status;

    /**
     * 消息状态
     */
    @TableField("message_status")
    private Integer messageStatus;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
