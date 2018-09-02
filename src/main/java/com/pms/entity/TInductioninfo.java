package com.pms.entity;

import javax.persistence.*;

@Table(name = "t_inductioninfo")
public class TInductioninfo {
    /**
     * 唯一标识
     */
    @Id
    @Column(name = "IND_ID")
    private String indId;

    /**
     * 员工工号
     */
    @Column(name = "EMP_NO")
    private String empNo;

    /**
     * 职位ID
     */
    @Column(name = "POS_ID")
    private String posId;

    /**
     * 申请时间
     */
    @Column(name = "IND_DATE")
    private String indDate;

    /**
     * 状态：0--在职；1--解聘
     */
    @Column(name = "IND_STATE")
    private String indState;

    /**
     * 离职时间
     */
    @Column(name = "IND_ENDDATE")
    private String indEnddate;

    /**
     * 申请状态：00 申请中;11 已审批通过;22 审批未通过;33 未提交【员工已增加但是未提交】可删除
     */
    @Column(name = "EXT1")
    private String ext1;

    /**
     * 审批日期
     */
    @Column(name = "EXT2")
    private String ext2;

    /**
     * 申请类型  IN 入职申请  OUT 离职申请
     */
    @Column(name = "EXT3")
    private String ext3;

    /**
     * 离职原因
     */
    @Column(name = "IND_Reasons")
    private String indReasons;

    /**
     * 获取唯一标识
     *
     * @return IND_ID - 唯一标识
     */
    public String getIndId() {
        return indId;
    }

    /**
     * 设置唯一标识
     *
     * @param indId 唯一标识
     */
    public void setIndId(String indId) {
        this.indId = indId;
    }

    /**
     * 获取员工工号
     *
     * @return EMP_NO - 员工工号
     */
    public String getEmpNo() {
        return empNo;
    }

    /**
     * 设置员工工号
     *
     * @param empNo 员工工号
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     * 获取职位ID
     *
     * @return POS_ID - 职位ID
     */
    public String getPosId() {
        return posId;
    }

    /**
     * 设置职位ID
     *
     * @param posId 职位ID
     */
    public void setPosId(String posId) {
        this.posId = posId;
    }

    /**
     * 获取申请时间
     *
     * @return IND_DATE - 申请时间
     */
    public String getIndDate() {
        return indDate;
    }

    /**
     * 设置申请时间
     *
     * @param indDate 申请时间
     */
    public void setIndDate(String indDate) {
        this.indDate = indDate;
    }

    /**
     * 获取状态：0--在职；1--解聘
     *
     * @return IND_STATE - 状态：0--在职；1--解聘
     */
    public String getIndState() {
        return indState;
    }

    /**
     * 设置状态：0--在职；1--解聘
     *
     * @param indState 状态：0--在职；1--解聘
     */
    public void setIndState(String indState) {
        this.indState = indState;
    }

    /**
     * 获取离职时间
     *
     * @return IND_ENDDATE - 离职时间
     */
    public String getIndEnddate() {
        return indEnddate;
    }

    /**
     * 设置离职时间
     *
     * @param indEnddate 离职时间
     */
    public void setIndEnddate(String indEnddate) {
        this.indEnddate = indEnddate;
    }

    /**
     * 获取申请状态：00 申请中;11 已审批通过;22 审批未通过;33 未提交【员工已增加但是未提交】可删除
     *
     * @return EXT1 - 申请状态：00 申请中;11 已审批通过;22 审批未通过;33 未提交【员工已增加但是未提交】可删除
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 设置申请状态：00 申请中;11 已审批通过;22 审批未通过;33 未提交【员工已增加但是未提交】可删除
     *
     * @param ext1 申请状态：00 申请中;11 已审批通过;22 审批未通过;33 未提交【员工已增加但是未提交】可删除
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    /**
     * 获取审批日期
     *
     * @return EXT2 - 审批日期
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 设置审批日期
     *
     * @param ext2 审批日期
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    /**
     * 获取申请类型  IN 入职申请  OUT 离职申请
     *
     * @return EXT3 - 申请类型  IN 入职申请  OUT 离职申请
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * 设置申请类型  IN 入职申请  OUT 离职申请
     *
     * @param ext3 申请类型  IN 入职申请  OUT 离职申请
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    /**
     * 获取离职原因
     *
     * @return IND_Reasons - 离职原因
     */
    public String getIndReasons() {
        return indReasons;
    }

    /**
     * 设置离职原因
     *
     * @param indReasons 离职原因
     */
    public void setIndReasons(String indReasons) {
        this.indReasons = indReasons;
    }
}