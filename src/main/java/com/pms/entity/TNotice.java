package com.pms.entity;

import javax.persistence.*;

@Table(name = "t_notice")
public class TNotice {
    /**
     * 唯一标识
     */
    @Id
    @Column(name = "NOT_ID")
    private String notId;

    /**
     * 标题
     */
    @Column(name = "NOT_TITLE")
    private String notTitle;

    /**
     * 公告内容
     */
    @Column(name = "NOT_CONTENT")
    private String notContent;

    /**
     * 添加时间
     */
    @Column(name = "NOT_DATE")
    private String notDate;

    /**
     * 作者
     */
    @Column(name = "NOT_AUTHOR")
    private String notAuthor;

    /**
     * 暂未使用
     */
    @Column(name = "EXT1")
    private String ext1;

    /**
     * 暂未使用
     */
    @Column(name = "EXT2")
    private String ext2;

    /**
     * 暂未使用
     */
    @Column(name = "EXT3")
    private String ext3;

    /**
     * 获取唯一标识
     *
     * @return NOT_ID - 唯一标识
     */
    public String getNotId() {
        return notId;
    }

    /**
     * 设置唯一标识
     *
     * @param notId 唯一标识
     */
    public void setNotId(String notId) {
        this.notId = notId;
    }

    /**
     * 获取标题
     *
     * @return NOT_TITLE - 标题
     */
    public String getNotTitle() {
        return notTitle;
    }

    /**
     * 设置标题
     *
     * @param notTitle 标题
     */
    public void setNotTitle(String notTitle) {
        this.notTitle = notTitle;
    }

    /**
     * 获取公告内容
     *
     * @return NOT_CONTENT - 公告内容
     */
    public String getNotContent() {
        return notContent;
    }

    /**
     * 设置公告内容
     *
     * @param notContent 公告内容
     */
    public void setNotContent(String notContent) {
        this.notContent = notContent;
    }

    /**
     * 获取添加时间
     *
     * @return NOT_DATE - 添加时间
     */
    public String getNotDate() {
        return notDate;
    }

    /**
     * 设置添加时间
     *
     * @param notDate 添加时间
     */
    public void setNotDate(String notDate) {
        this.notDate = notDate;
    }

    /**
     * 获取作者
     *
     * @return NOT_AUTHOR - 作者
     */
    public String getNotAuthor() {
        return notAuthor;
    }

    /**
     * 设置作者
     *
     * @param notAuthor 作者
     */
    public void setNotAuthor(String notAuthor) {
        this.notAuthor = notAuthor;
    }

    /**
     * 获取暂未使用
     *
     * @return EXT1 - 暂未使用
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 设置暂未使用
     *
     * @param ext1 暂未使用
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    /**
     * 获取暂未使用
     *
     * @return EXT2 - 暂未使用
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 设置暂未使用
     *
     * @param ext2 暂未使用
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    /**
     * 获取暂未使用
     *
     * @return EXT3 - 暂未使用
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * 设置暂未使用
     *
     * @param ext3 暂未使用
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
}