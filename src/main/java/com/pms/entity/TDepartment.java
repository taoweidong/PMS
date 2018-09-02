package com.pms.entity;

import javax.persistence.*;

@Table(name = "t_department")
public class TDepartment {
    /**
     * 唯一标识
     */
    @Id
    @Column(name = "DEP_ID")
    private String depId;

    /**
     * 部门名称
     */
    @Column(name = "DEP_NAME")
    private String depName;

    /**
     * 部门领导
     */
    @Column(name = "DEP_LEADER")
    private String depLeader;

    /**
     * 获取唯一标识
     *
     * @return DEP_ID - 唯一标识
     */
    public String getDepId() {
        return depId;
    }

    /**
     * 设置唯一标识
     *
     * @param depId 唯一标识
     */
    public void setDepId(String depId) {
        this.depId = depId;
    }

    /**
     * 获取部门名称
     *
     * @return DEP_NAME - 部门名称
     */
    public String getDepName() {
        return depName;
    }

    /**
     * 设置部门名称
     *
     * @param depName 部门名称
     */
    public void setDepName(String depName) {
        this.depName = depName;
    }

    /**
     * 获取部门领导
     *
     * @return DEP_LEADER - 部门领导
     */
    public String getDepLeader() {
        return depLeader;
    }

    /**
     * 设置部门领导
     *
     * @param depLeader 部门领导
     */
    public void setDepLeader(String depLeader) {
        this.depLeader = depLeader;
    }
}