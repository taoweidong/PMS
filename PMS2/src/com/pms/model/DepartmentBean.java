package com.pms.model;

/**
 * 
 * @author 陶伟东
 * TODO：部门实体
 * 编写时间：2017-4-8-上午10:04:44
 */
public class DepartmentBean {

	private String dep_Id;
	private String dep_Name;
	private String dep_Leader;

	@Override
	public String toString() {
		return "部门实体 [dep_Id=" + dep_Id + ", dep_Name=" + dep_Name
				+ ", dep_Leader=" + dep_Leader + "]";
	}

	public DepartmentBean() {
		super();
	}

	public DepartmentBean(String dep_Id, String dep_Name, String dep_Leader) {
		super();
		this.dep_Id = dep_Id;
		this.dep_Name = dep_Name;
		this.dep_Leader = dep_Leader;
	}

	public String getDep_Id() {
		return dep_Id;
	}

	public void setDep_Id(String dep_Id) {
		this.dep_Id = dep_Id;
	}

	public String getDep_Name() {
		return dep_Name;
	}

	public void setDep_Name(String dep_Name) {
		this.dep_Name = dep_Name;
	}

	public String getDep_Leader() {
		return dep_Leader;
	}

	public void setDep_Leader(String dep_Leader) {
		this.dep_Leader = dep_Leader;
	}

}
