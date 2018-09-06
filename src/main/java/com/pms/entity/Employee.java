package com.pms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_employee")
public class Employee {
    /**
     * 登录账号
     */
    @Id
    @Column(name = "EMP_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String no;

    /**
     * 密码，采用ASE加密
     */
    @Column(name = "EMP_PWD")
    private String pwd;

    /**
     * 员工姓名
     */
    @Column(name = "EMP_NAME")
    private String name;

    /**
     * 性别：M-男;F-女
     */
    @Column(name = "EMP_SEX")
    private String sex;

    /**
     * 生日
     */
    @Column(name = "EMP_Birthday")
    private Date birthday;

    /**
     * 政治面貌
     */
    @Column(name = "PS_ID")
    private String psId;

    /**
     * 电话
     */
    @Column(name = "EMP_Phone")
    private String phone;

    /**
     * 联系地址
     */
    @Column(name = "EMP_Address")
    private String address;

    /**
     * 备注
     */
    private String ext1;

    /**
     * 更新时间
     */
    private String ext2;

    /**
     * 暂未使用
     */
    private String ext3;

    /**
     * 获取登录账号
     *
     * @return EMP_NO - 登录账号
     */
    public String getNo() {
        return no;
    }

    /**
     * 设置登录账号
     *
     * @param no 登录账号
     */
    public void setNo(final String no) {
        this.no = no;
    }

    /**
     * 获取密码，采用ASE加密
     *
     * @return EMP_PWD - 密码，采用ASE加密
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置密码，采用ASE加密
     *
     * @param pwd 密码，采用ASE加密
     */
    public void setPwd(final String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取员工姓名
     *
     * @return EMP_NAME - 员工姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置员工姓名
     *
     * @param name 员工姓名
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 获取性别：M-男;F-女
     *
     * @return EMP_SEX - 性别：M-男;F-女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别：M-男;F-女
     *
     * @param sex 性别：M-男;F-女
     */
    public void setSex(final String sex) {
        this.sex = sex;
    }

    /**
     * 获取生日
     *
     * @return EMP_Birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取政治面貌
     *
     * @return PS_ID - 政治面貌
     */
    public String getPsId() {
        return psId;
    }

    /**
     * 设置政治面貌
     *
     * @param psId 政治面貌
     */
    public void setPsId(final String psId) {
        this.psId = psId;
    }

    /**
     * 获取电话
     *
     * @return EMP_Phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * 获取联系地址
     *
     * @return EMP_Address - 联系地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置联系地址
     *
     * @param address 联系地址
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * 获取备注
     *
     * @return ext1 - 备注
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 设置备注
     *
     * @param ext1 备注
     */
    public void setExt1(final String ext1) {
        this.ext1 = ext1;
    }

    /**
     * 获取更新时间
     *
     * @return ext2 - 更新时间
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 设置更新时间
     *
     * @param ext2 更新时间
     */
    public void setExt2(final String ext2) {
        this.ext2 = ext2;
    }

    /**
     * 获取暂未使用
     *
     * @return ext3 - 暂未使用
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * 设置暂未使用
     *
     * @param ext3 暂未使用
     */
    public void setExt3(final String ext3) {
        this.ext3 = ext3;
    }
}