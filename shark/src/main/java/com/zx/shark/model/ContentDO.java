package com.zx.shark.model;
import java.io.Serializable;
import java.sql.Date;

/**
 * 文本内容
 */
public class ContentDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long cid;
    //标题
    private String title;
    //
    private String slug;
    //创建人id
    private Long created;
    //最近修改人id
    private Long modified;
    //内容
    private String content;
    //类型
    private String type;
    //标签
    private String tags;
    //分类
    private String categories;
    //
    private Integer hits;
    //评论数量
    private Integer comments_num;
    //开启评论
    private Integer allow_comment;
    //允许ping
    private Integer allow_ping;
    //允许反馈
    private Integer allow_feed;
    //状态
    private Integer status;
    //作者
    private String author;
    //创建时间
    private Date gtm_create;
    //修改时间
    private Date gtm_modified;

    /**
     * 设置：
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }
    /**
     * 获取：
     */
    public Long getCid() {
        return cid;
    }
    /**
     * 设置：标题
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * 获取：标题
     */
    public String getTitle() {
        return title;
    }
    /**
     * 设置：
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }
    /**
     * 获取：
     */
    public String getSlug() {
        return slug;
    }
    /**
     * 设置：创建人id
     */
    public void setCreated(Long created) {
        this.created = created;
    }
    /**
     * 获取：创建人id
     */
    public Long getCreated() {
        return created;
    }
    /**
     * 设置：最近修改人id
     */
    public void setModified(Long modified) {
        this.modified = modified;
    }
    /**
     * 获取：最近修改人id
     */
    public Long getModified() {
        return modified;
    }
    /**
     * 设置：内容
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * 获取：内容
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：类型
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * 获取：类型
     */
    public String getType() {
        return type;
    }
    /**
     * 设置：标签
     */
    public void setTags(String tags) {
        this.tags = tags;
    }
    /**
     * 获取：标签
     */
    public String getTags() {
        return tags;
    }
    /**
     * 设置：分类
     */
    public void setCategories(String categories) {
        this.categories = categories;
    }
    /**
     * 获取：分类
     */
    public String getCategories() {
        return categories;
    }
    /**
     * 设置：
     */
    public void setHits(Integer hits) {
        this.hits = hits;
    }
    /**
     * 获取：
     */
    public Integer getHits() {
        return hits;
    }
    /**
     * 设置：评论数量
     */
    public void setComments_num(Integer comments_num) {
        this.comments_num = comments_num;
    }
    /**
     * 获取：评论数量
     */
    public Integer getComments_num() {
        return comments_num;
    }
    /**
     * 设置：开启评论
     */
    public void setAllow_comment(Integer allow_comment) {
        this.allow_comment = allow_comment;
    }
    /**
     * 获取：开启评论
     */
    public Integer getAllow_comment() {
        return allow_comment;
    }
    /**
     * 设置：允许ping
     */
    public void setAllow_ping(Integer allow_ping) {
        this.allow_ping = allow_ping;
    }
    /**
     * 获取：允许ping
     */
    public Integer getAllow_ping() {
        return allow_ping;
    }
    /**
     * 设置：允许反馈
     */
    public void setAllow_feed(Integer allow_feed) {
        this.allow_feed = allow_feed;
    }
    /**
     * 获取：允许反馈
     */
    public Integer getAllow_feed() {
        return allow_feed;
    }
    /**
     * 设置：状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * 获取：状态
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置：作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * 获取：作者
     */
    public String getAuthor() {
        return author;
    }
    /**
     * 设置：创建时间
     */
    public void setGtm_create(Date gtm_create) {
        this.gtm_create = gtm_create;
    }
    /**
     * 获取：创建时间
     */
    public Date getGtm_create() {
        return gtm_create;
    }
    /**
     * 设置：修改时间
     */
    public void setGtm_modified(Date gtm_modified) {
        this.gtm_modified = gtm_modified;
    }
    /**
     * 获取：修改时间
     */
    public Date getGtm_modified() {
        return gtm_modified;
    }

    @Override
    public String toString() {
        return "ContentDO{" +
                "cid=" + cid +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", tags='" + tags + '\'' +
                ", categories='" + categories + '\'' +
                ", hits=" + hits +
                ", commentsNum=" + comments_num +
                ", allowComment=" + allow_comment +
                ", allowPing=" + allow_ping +
                ", allowFeed=" + allow_feed +
                ", status=" + status +
                ", author='" + author + '\'' +
                ", gtmCreate=" + gtm_create +
                ", gtmModified=" + gtm_modified +
                '}';
    }
}

