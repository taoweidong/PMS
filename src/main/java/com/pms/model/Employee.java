package com.pms.model;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {

	/**
	 * 序列化ID<br>
	 */
	private static final long serialVersionUID = -9128397329749221994L;

	private String emp_no;

	private String emp_pwd;

	private String emp_name;

	private String emp_sex;

	private Date emp_birthday;

	private String ps_id;

	private String ps_name;

	public String getPs_name() {
		return ps_name;
	}

	public void setPs_name(String ps_name) {
		this.ps_name = ps_name;
	}

	private String emp_phone;

	private String emp_address;

	private String ext1;

	private String ext2;

	private String ext3;

	public Employee(String emp_no, String emp_pwd, String emp_name, String emp_sex,
			Date emp_birthday, String ps_id, String emp_phone, String emp_address, String ext1,
			String ext2, String ext3) {
		super();
		this.emp_no = emp_no;
		this.emp_pwd = emp_pwd;
		this.emp_name = emp_name;
		this.emp_sex = emp_sex;
		this.emp_birthday = emp_birthday;
		this.ps_id = ps_id;
		this.emp_phone = emp_phone;
		this.emp_address = emp_address;
		this.ext1 = ext1;
		this.ext2 = ext2;
		this.ext3 = ext3;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getEmp_pwd() {
		return emp_pwd;
	}

	public void setEmp_pwd(String emp_pwd) {
		this.emp_pwd = emp_pwd;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_sex() {
		return emp_sex;
	}

	public void setEmp_sex(String emp_sex) {
		this.emp_sex = emp_sex;
	}

	public Date getEmp_birthday() {
		return emp_birthday;
	}

	public void setEmp_birthday(Date emp_birthday) {
		this.emp_birthday = emp_birthday;
	}

	public String getPs_id() {
		return ps_id;
	}

	public void setPs_id(String ps_id) {
		this.ps_id = ps_id;
	}

	public String getEmp_phone() {
		return emp_phone;
	}

	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}

	public String getEmp_address() {
		return emp_address;
	}

	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	@Override
	public String toString() {
		return "Employee [emp_no=" + emp_no + ", emp_pwd=" + emp_pwd + ", emp_name=" + emp_name
				+ ", emp_sex=" + emp_sex + ", emp_birthday=" + emp_birthday + ", ps_id=" + ps_id
				+ ", ps_name=" + ps_name + ", emp_phone=" + emp_phone + ", emp_address="
				+ emp_address + ", ext1=" + ext1 + ", ext2=" + ext2 + ", ext3=" + ext3 + "]";
	}

}
