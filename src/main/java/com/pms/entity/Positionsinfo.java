package com.pms.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "t_positionsinfo")
public class Positionsinfo {
    /**
     * 唯一标识
     */
    @Id
    @Column(name = "POS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 部门ID
     */
    @Column(name = "DEP_ID")
    private String depId;

    /**
     * 岗位名称
     */
    @Column(name = "POS_NAME")
    private String name;

    /**
     * 岗位基本工资
     */
    @Column(name = "POS_SALARY")
    private BigDecimal salary;

    /**
     * 岗位津贴
     */
    @Column(name = "POS_ALLOWANCE")
    private BigDecimal allowance;

    /**
     * 特殊津贴
     */
    @Column(name = "POS_PERQUISITES")
    private BigDecimal perquisites;

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
    private String content;

    /**
     * 获取唯一标识
     *
     * @return POS_ID - 唯一标识
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
    public void setDepId(final String depId) {
        this.depId = depId;
    }

    /**
     * 获取岗位名称
     *
     * @return POS_NAME - 岗位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置岗位名称
     *
     * @param name 岗位名称
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 获取岗位基本工资
     *
     * @return POS_SALARY - 岗位基本工资
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * 设置岗位基本工资
     *
     * @param salary 岗位基本工资
     */
    public void setSalary(final BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * 获取岗位津贴
     *
     * @return POS_ALLOWANCE - 岗位津贴
     */
    public BigDecimal getAllowance() {
        return allowance;
    }

    /**
     * 设置岗位津贴
     *
     * @param allowance 岗位津贴
     */
    public void setAllowance(final BigDecimal allowance) {
        this.allowance = allowance;
    }

    /**
     * 获取特殊津贴
     *
     * @return POS_PERQUISITES - 特殊津贴
     */
    public BigDecimal getPerquisites() {
        return perquisites;
    }

    /**
     * 设置特殊津贴
     *
     * @param perquisites 特殊津贴
     */
    public void setPerquisites(final BigDecimal perquisites) {
        this.perquisites = perquisites;
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
    public void setExt1(final String ext1) {
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
    public void setExt2(final String ext2) {
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
    public void setExt3(final String ext3) {
        this.ext3 = ext3;
    }

    /**
     * 获取岗位职责
     *
     * @return POS_CONTENT - 岗位职责
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置岗位职责
     *
     * @param content 岗位职责
     */
    public void setContent(final String content) {
        this.content = content;
    }
}