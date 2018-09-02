package com.pms.entity;

import javax.persistence.*;

@Table(name = "t_administrator")
public class TAdministrator {
    /**
     * 唯一标识
     */
    @Id
    @Column(name = "ADMIN_ID")
    private String adminId;

    /**
     * 登录账号
     */
    @Column(name = "ADMIN_NO")
    private String adminNo;

    /**
     * 密码，采用ASE加密
     */
    @Column(name = "ADMIN_PWD")
    private String adminPwd;

    /**
     * 管理员姓名
     */
    @Column(name = "ADMIN_NAME")
    private String adminName;

    /**
     * 电话
     */
    @Column(name = "ADMIN_PHONE")
    private String adminPhone;

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
     *
     * @return ADMIN_ID - 唯一标识
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     * 设置唯一标识
     *
     * @param adminId 唯一标识
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    /**
     * 获取登录账号
     *
     * @return ADMIN_NO - 登录账号
     */
    public String getAdminNo() {
        return adminNo;
    }

    /**
     * 设置登录账号
     *
     * @param adminNo 登录账号
     */
    public void setAdminNo(String adminNo) {
        this.adminNo = adminNo;
    }

    /**
     * 获取密码，采用ASE加密
     *
     * @return ADMIN_PWD - 密码，采用ASE加密
     */
    public String getAdminPwd() {
        return adminPwd;
    }

    /**
     * 设置密码，采用ASE加密
     *
     * @param adminPwd 密码，采用ASE加密
     */
    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    /**
     * 获取管理员姓名
     *
     * @return ADMIN_NAME - 管理员姓名
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * 设置管理员姓名
     *
     * @param adminName 管理员姓名
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * 获取电话
     *
     * @return ADMIN_PHONE - 电话
     */
    public String getAdminPhone() {
        return adminPhone;
    }

    /**
     * 设置电话
     *
     * @param adminPhone 电话
     */
    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    /**
     * 获取备注
     *
     * @return Ext1 - 备注
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 设置备注
     *
     * @param ext1 备注
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    /**
     * 获取更新时间
     *
     * @return Ext2 - 更新时间
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 设置更新时间
     *
     * @param ext2 更新时间
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    /**
     * 获取admin 普通管理员;superAdmin 超级管理员;
     *
     * @return Ext3 - admin 普通管理员;superAdmin 超级管理员;
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * 设置admin 普通管理员;superAdmin 超级管理员;
     *
     * @param ext3 admin 普通管理员;superAdmin 超级管理员;
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
}