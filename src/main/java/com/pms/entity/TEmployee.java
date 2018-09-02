package com.pms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_employee")
public class TEmployee {
    /**
     * 登录账号
     */
    @Id
    @Column(name = "EMP_NO")
    private String empNo;

    /**
     * 密码，采用ASE加密
     */
    @Column(name = "EMP_PWD")
    private String empPwd;

    /**
     * 员工姓名
     */
    @Column(name = "EMP_NAME")
    private String empName;

    /**
     * 性别：M-男;F-女
     */
    @Column(name = "EMP_SEX")
    private String empSex;

    /**
     * 生日
     */
    @Column(name = "EMP_Birthday")
    private Date empBirthday;

    /**
     * 政治面貌
     */
    @Column(name = "PS_ID")
    private String psId;

    /**
     * 电话
     */
    @Column(name = "EMP_Phone")
    private String empPhone;

    /**
     * 联系地址
     */
    @Column(name = "EMP_Address")
    private String empAddress;

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
    public String getEmpNo() {
        return empNo;
    }

    /**
     * 设置登录账号
     *
     * @param empNo 登录账号
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     * 获取密码，采用ASE加密
     *
     * @return EMP_PWD - 密码，采用ASE加密
     */
    public String getEmpPwd() {
        return empPwd;
    }

    /**
     * 设置密码，采用ASE加密
     *
     * @param empPwd 密码，采用ASE加密
     */
    public void setEmpPwd(String empPwd) {
        this.empPwd = empPwd;
    }

    /**
     * 获取员工姓名
     *
     * @return EMP_NAME - 员工姓名
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * 设置员工姓名
     *
     * @param empName 员工姓名
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * 获取性别：M-男;F-女
     *
     * @return EMP_SEX - 性别：M-男;F-女
     */
    public String getEmpSex() {
        return empSex;
    }

    /**
     * 设置性别：M-男;F-女
     *
     * @param empSex 性别：M-男;F-女
     */
    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }

    /**
     * 获取生日
     *
     * @return EMP_Birthday - 生日
     */
    public Date getEmpBirthday() {
        return empBirthday;
    }

    /**
     * 设置生日
     *
     * @param empBirthday 生日
     */
    public void setEmpBirthday(Date empBirthday) {
        this.empBirthday = empBirthday;
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
    public void setPsId(String psId) {
        this.psId = psId;
    }

    /**
     * 获取电话
     *
     * @return EMP_Phone - 电话
     */
    public String getEmpPhone() {
        return empPhone;
    }

    /**
     * 设置电话
     *
     * @param empPhone 电话
     */
    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    /**
     * 获取联系地址
     *
     * @return EMP_Address - 联系地址
     */
    public String getEmpAddress() {
        return empAddress;
    }

    /**
     * 设置联系地址
     *
     * @param empAddress 联系地址
     */
    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
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
    public void setExt1(String ext1) {
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
    public void setExt2(String ext2) {
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
    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
}