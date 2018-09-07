package com.pms.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_administrator")
public class Administrator {
	/**
	 * 唯一标识
	 */
	@Id
	@Column(name = "ADMIN_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	/**
	 * 登录账号
	 */
	@Column(name = "ADMIN_NO")
	private String no;

	/**
	 * 密码，采用ASE加密
	 */
	@Column(name = "ADMIN_PWD")
	private String pwd;

	/**
	 * 管理员姓名
	 */
	@Column(name = "ADMIN_NAME")
	private String name;

	/**
	 * 电话
	 */
	@Column(name = "ADMIN_PHONE")
	private String phone;

	/**
	 * 备注
	 */
	@Column(name = "Ext1")
	private String ext1;

	/**
	 * 更新时间
	 */
	@Column(name = "Ext2")
	private String ext2;

	/**
	 * admin 普通管理员;superAdmin 超级管理员;
	 */
	@Column(name = "Ext3")
	private String ext3;

	/**
	 * 获取唯一标识
	 * @return ADMIN_ID - 唯一标识
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
	 * 获取登录账号
	 * @return ADMIN_NO - 登录账号
	 */
	public String getNo() {
		return no;
	}

	/**
	 * 设置登录账号
	 * @param no 登录账号
	 */
	public void setNo(final String no) {
		this.no = no;
	}

	/**
	 * 获取密码，采用ASE加密
	 * @return ADMIN_PWD - 密码，采用ASE加密
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * 设置密码，采用ASE加密
	 * @param pwd 密码，采用ASE加密
	 */
	public void setPwd(final String pwd) {
		this.pwd = pwd;
	}

	/**
	 * 获取管理员姓名
	 * @return ADMIN_NAME - 管理员姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置管理员姓名
	 * @param name 管理员姓名
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * 获取电话
	 * @return ADMIN_PHONE - 电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置电话
	 * @param phone 电话
	 */
	public void setPhone(final String phone) {
		this.phone = phone;
	}

	/**
	 * 获取备注
	 * @return Ext1 - 备注
	 */
	public String getExt1() {
		return ext1;
	}

	/**
	 * 设置备注
	 * @param ext1 备注
	 */
	public void setExt1(final String ext1) {
		this.ext1 = ext1;
	}

	/**
	 * 获取更新时间
	 * @return Ext2 - 更新时间
	 */
	public String getExt2() {
		return ext2;
	}

	/**
	 * 设置更新时间
	 * @param ext2 更新时间
	 */
	public void setExt2(final String ext2) {
		this.ext2 = ext2;
	}

	/**
	 * 获取admin 普通管理员;superAdmin 超级管理员;
	 * @return Ext3 - admin 普通管理员;superAdmin 超级管理员;
	 */
	public String getExt3() {
		return ext3;
	}

	/**
	 * 设置admin 普通管理员;superAdmin 超级管理员;
	 * @param ext3 admin 普通管理员;superAdmin 超级管理员;
	 */
	public void setExt3(final String ext3) {
		this.ext3 = ext3;
	}

	/** Default constructor */
	public Administrator() {
	}
}