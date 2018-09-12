package com.pms.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_notice")
public class Notice {
	/**
	 * 唯一标识
	 */
	@Id
	@Column(name = "NOT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	/**
	 * 标题
	 */
	@Column(name = "NOT_TITLE")
	private String title;

	/**
	 * 公告内容
	 */
	@Column(name = "NOT_CONTENT")
	private String content;

	/**
	 * 添加时间
	 */
	@Column(name = "NOT_DATE")
	private String date;

	/**
	 * 作者
	 */
	@Column(name = "NOT_AUTHOR")
	private String author;

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
	 * @return NOT_ID - 唯一标识
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置唯一标识
	 * @param id 唯一标识
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * 获取标题
	 * @return NOT_TITLE - 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * @param title 标题
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * 获取公告内容
	 * @return NOT_CONTENT - 公告内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置公告内容
	 * @param content 公告内容
	 */
	public void setContent(final String content) {
		this.content = content;
	}

	/**
	 * 获取添加时间
	 * @return NOT_DATE - 添加时间
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 设置添加时间
	 * @param date 添加时间
	 */
	public void setDate(final String date) {
		this.date = date;
	}

	/**
	 * 获取作者
	 * @return NOT_AUTHOR - 作者
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 设置作者
	 * @param author 作者
	 */
	public void setAuthor(final String author) {
		this.author = author;
	}

	/**
	 * 获取暂未使用
	 * @return EXT1 - 暂未使用
	 */
	public String getExt1() {
		return ext1;
	}

	/**
	 * 设置暂未使用
	 * @param ext1 暂未使用
	 */
	public void setExt1(final String ext1) {
		this.ext1 = ext1;
	}

	/**
	 * 获取暂未使用
	 * @return EXT2 - 暂未使用
	 */
	public String getExt2() {
		return ext2;
	}

	/**
	 * 设置暂未使用
	 * @param ext2 暂未使用
	 */
	public void setExt2(final String ext2) {
		this.ext2 = ext2;
	}

	/**
	 * 获取暂未使用
	 * @return EXT3 - 暂未使用
	 */
	public String getExt3() {
		return ext3;
	}

	/**
	 * 设置暂未使用
	 * @param ext3 暂未使用
	 */
	public void setExt3(final String ext3) {
		this.ext3 = ext3;
	}

	/** Default constructor */
	public Notice() {
	}
}