package com.pms.model;

/**
 * 用户Model类
 * 
 * @author
 */
public class Administrator
{

    private String admin_id;

    private String admin_no;

    private String admin_pwd;

    private String admin_name;

    private String admin_phone;

    private String ext1;

    private String ext2;

    private String ext3;

    public Administrator()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public Administrator(String admin_id, String admin_no, String admin_pwd, String admin_name,
                         String admin_phone, String ext1, String ext2, String ext3)
    {
        super();
        this.admin_id = admin_id;
        this.admin_no = admin_no;
        this.admin_pwd = admin_pwd;
        this.admin_name = admin_name;
        this.admin_phone = admin_phone;
        this.ext1 = ext1;
        this.ext2 = ext2;
        this.ext3 = ext3;
    }

    @Override
    public String toString()
    {
        return "管理员实体类信息 [admin_id=" + admin_id + ", admin_no=" + admin_no + ", admin_pwd="
               + admin_pwd + ", admin_name=" + admin_name + ", admin_phone=" + admin_phone
               + ", ext1=" + ext1 + ", ext2=" + ext2 + ", ext3=" + ext3 + "]";
    }

    public String getAdmin_id()
    {
        return admin_id;
    }

    public void setAdmin_id(String admin_id)
    {
        this.admin_id = admin_id;
    }

    public String getAdmin_no()
    {
        return admin_no;
    }

    public void setAdmin_no(String admin_no)
    {
        this.admin_no = admin_no;
    }

    public String getAdmin_pwd()
    {
        return admin_pwd;
    }

    public void setAdmin_pwd(String admin_pwd)
    {
        this.admin_pwd = admin_pwd;
    }

    public String getAdmin_name()
    {
        return admin_name;
    }

    public void setAdmin_name(String admin_name)
    {
        this.admin_name = admin_name;
    }

    public String getAdmin_phone()
    {
        return admin_phone;
    }

    public void setAdmin_phone(String admin_phone)
    {
        this.admin_phone = admin_phone;
    }

    public String getExt1()
    {
        return ext1;
    }

    public void setExt1(String ext1)
    {
        this.ext1 = ext1;
    }

    public String getExt2()
    {
        return ext2;
    }

    public void setExt2(String ext2)
    {
        this.ext2 = ext2;
    }

    public String getExt3()
    {
        return ext3;
    }

    public void setExt3(String ext3)
    {
        this.ext3 = ext3;
    }

}
