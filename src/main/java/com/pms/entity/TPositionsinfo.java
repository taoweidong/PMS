package com.pms.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "t_positionsinfo")
public class TPositionsinfo {
    /**
     * 唯一标识
     */
    @Id
    @Column(name = "POS_ID")
    private String posId;

    /**
     * 部门ID
     */
    @Column(name = "DEP_ID")
    private String depId;

    /**
     * 岗位名称
     */
    @Column(name = "POS_NAME")
    private String posName;

    /**
     * 岗位基本工资
     */
    @Column(name = "POS_SALARY")
    private BigDecimal posSalary;

    /**
     * 岗位津贴
     */
    @Column(name = "POS_ALLOWANCE")
    private BigDecimal posAllowance;

    /**
     * 特殊津贴
     */
    @Column(name = "POS_PERQUISITES")
    private BigDecimal posPerquisites;

    /**
     * 备注
     */
    @Column(name = "EXT1")
    private String ext1;

    /**
     * 更新时间
     */
    @Column(name = "EXT2")
    private String ext2;

    /**
     * 暂未使用
     */
    @Column(name = "EXT3")
    private String ext3;

    /**
     * 岗位职责
     */
    @Column(name = "POS_CONTENT")
    private String posContent;

    /**
     * 获取唯一标识
     *
     * @return POS_ID - 唯一标识
     */
    public String getPosId() {
        return posId;
    }

    /**
     * 设置唯一标识
     *
     * @param posId 唯一标识
     */
    public void setPosId(String posId) {
        this.posId = posId;
    }

    /**
     * 获取部门ID
     *
     * @return DEP_ID - 部门ID
     */
    public String getDepId() {
        return depId;
    }

    /**
     * 设置部门ID
     *
     * @param depId 部门ID
     */
    public void setDepId(String depId) {
        this.depId = depId;
    }

    /**
     * 获取岗位名称
     *
     * @return POS_NAME - 岗位名称
     */
    public String getPosName() {
        return posName;
    }

    /**
     * 设置岗位名称
     *
     * @param posName 岗位名称
     */
    public void setPosName(String posName) {
        this.posName = posName;
    }

    /**
     * 获取岗位基本工资
     *
     * @return POS_SALARY - 岗位基本工资
     */
    public BigDecimal getPosSalary() {
        return posSalary;
    }

    /**
     * 设置岗位基本工资
     *
     * @param posSalary 岗位基本工资
     */
    public void setPosSalary(BigDecimal posSalary) {
        this.posSalary = posSalary;
    }

    /**
     * 获取岗位津贴
     *
     * @return POS_ALLOWANCE - 岗位津贴
     */
    public BigDecimal getPosAllowance() {
        return posAllowance;
    }

    /**
     * 设置岗位津贴
     *
     * @param posAllowance 岗位津贴
     */
    public void setPosAllowance(BigDecimal posAllowance) {
        this.posAllowance = posAllowance;
    }

    /**
     * 获取特殊津贴
     *
     * @return POS_PERQUISITES - 特殊津贴
     */
    public BigDecimal getPosPerquisites() {
        return posPerquisites;
    }

    /**
     * 设置特殊津贴
     *
     * @param posPerquisites 特殊津贴
     */
    public void setPosPerquisites(BigDecimal posPerquisites) {
        this.posPerquisites = posPerquisites;
    }

    /**
     * 获取备注
     *
     * @return EXT1 - 备注
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
     * @return EXT2 - 更新时间
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

    /**
     * 获取岗位职责
     *
     * @return POS_CONTENT - 岗位职责
     */
    public String getPosContent() {
        return posContent;
    }

    /**
     * 设置岗位职责
     *
     * @param posContent 岗位职责
     */
    public void setPosContent(String posContent) {
        this.posContent = posContent;
    }
}