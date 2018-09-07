package com.pms.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_inductioninfo")
public class Inductioninfo {
    /**
     * 唯一标识
     */
    @Id
    @Column(name = "IND_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

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
    private String date;

    /**
     * 状态：0--在职；1--解聘
     */
    @Column(name = "IND_STATE")
    private String state;

    /**
     * 离职时间
     */
    @Column(name = "IND_ENDDATE")
    private String enddate;

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
    private String reasons;

    /**
     * 获取唯一标识
     *
     * @return IND_ID - 唯一标识
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一标识
     *
     * @param id 唯一标识
     */
    public void setId(final String id) {
        this.id = id;
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
    public void setEmpNo(final String empNo) {
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
    public void setPosId(final String posId) {
        this.posId = posId;
    }

    /**
     * 获取申请时间
     *
     * @return IND_DATE - 申请时间
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置申请时间
     *
     * @param date 申请时间
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * 获取状态：0--在职；1--解聘
     *
     * @return IND_STATE - 状态：0--在职；1--解聘
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态：0--在职；1--解聘
     *
     * @param state 状态：0--在职；1--解聘
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * 获取离职时间
     *
     * @return IND_ENDDATE - 离职时间
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * 设置离职时间
     *
     * @param enddate 离职时间
     */
    public void setEnddate(final String enddate) {
        this.enddate = enddate;
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
    public void setExt1(final String ext1) {
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
    public void setExt2(final String ext2) {
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
    public void setExt3(final String ext3) {
        this.ext3 = ext3;
    }

    /**
     * 获取离职原因
     *
     * @return IND_Reasons - 离职原因
     */
    public String getReasons() {
        return reasons;
    }

    /**
     * 设置离职原因
     *
     * @param reasons 离职原因
     */
    public void setReasons(final String reasons) {
        this.reasons = reasons;
    }

	/** Default constructor */
	public Inductioninfo() {
	}
}