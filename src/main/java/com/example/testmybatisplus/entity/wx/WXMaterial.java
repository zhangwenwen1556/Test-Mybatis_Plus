package com.example.testmybatisplus.entity.wx;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.example.testmybatisplus.constants.Constants;
import lombok.Data;
import org.apache.tomcat.util.bcel.Const;

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
 * @desc 微信素材
 */
@Data
@TableName("wx_material")
public class WXMaterial extends Model<WXMaterial> {

    /**
     * 是否显示封面，数字中文对照
     */
    public final static Map<Integer, String> SHOW_COVER_PIC = new HashMap<Integer, String>(){{
        put(Constants.SHOW_COVER_PIC_NO, Constants.SHOW_COVER_PIC_NO_NAME);
        put(Constants.SHOW_COVER_PIC_YES, Constants.SHOW_COVER_PIC_YES_NAME);
    }};


    /**
     * 系统ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 图文消息的封面图片素材id（必须是永久mediaID）
     */
    @TableField("thumb_media_id")
    private String thumbMediaId;

    /**
     * 作者
     */
    @TableField("author")
    private String author;

    /**
     * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字。
     */
    @TableField("digest")
    private String digest;

    /**
     * 是否显示封面，0为false，即不显示，1为true，即显示
     * {@link Constants#SHOW_COVER_PIC_NO}
     * {@link Constants#SHOW_COVER_PIC_YES}
     */
    @TableField("show_cover_pic")
    private String showCoverPic;

    /**
     * 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 "上传文消息内的图片获取URL"接口获取。外部图片url将被过滤。
     */
    @TableField("content")
    private String content;

    /**
     * 图文消息的原文地址，即点击“阅读原文”后的URL
     */
    @TableField("content_source_url")
    private String contentSourceUrl;

    /**
     * 微信系统中的ID
     */
    @TableField("media_id")
    private String mediaId;

    /**
     * 在系统中的创建时间
     */
    @TableField("create_at")
    private Date createAt;

    /**
     * 系统状态
     * {@link Constants#STATUS_INVALID}
     * {@link Constants#STATUS_VALID}
     */
    @TableField("status")
    private Integer status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
