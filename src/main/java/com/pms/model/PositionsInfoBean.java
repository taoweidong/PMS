package com.pms.model;

/**
 * @author Taowd TODO：存储岗位信息实体 编写时间：2017-4-8-上午10:10:03
 */
public class PositionsInfoBean
{

    private String pos_Id;

    private String dep_Id;

    private String pos_Name;

    private String pos_Content;

    private double pos_Salary;

    private double pos_Allowance;

    private double pos_Perquisites;

    private String ext1;

    private String ext2;

    private String ext3;

    @Override
    public String toString()
    {
        return "岗位信息实体 [pos_Id=" + pos_Id + ", dep_Id=" + dep_Id + ", pos_Name=" + pos_Name
               + ", pox_Content=" + pos_Content + ", pos_Salary=" + pos_Salary + ", pos_Allowance="
               + pos_Allowance + ", pos_Perquisites=" + pos_Perquisites + ", ext1=" + ext1
               + ", ext2=" + ext2 + ", ext3=" + ext3 + "]";
    }

    public PositionsInfoBean()
    {
        super();
    }

    public PositionsInfoBean(String pos_Id, String dep_Id, String pos_Name, String pox_Content,
                             double pos_Salary, double pos_Allowance, double pos_Perquisites,
                             String ext1, String ext2, String ext3)
    {
        super();
        this.pos_Id = pos_Id;
        this.dep_Id = dep_Id;
        this.pos_Name = pos_Name;
        this.pos_Content = pox_Content;
        this.pos_Salary = pos_Salary;
        this.pos_Allowance = pos_Allowance;
        this.pos_Perquisites = pos_Perquisites;
        this.ext1 = ext1;
        this.ext2 = ext2;
        this.ext3 = ext3;
    }

    public String getPos_Id()
    {
        return pos_Id;
    }

    public void setPos_Id(String pos_Id)
    {
        this.pos_Id = pos_Id;
    }

    public String getDep_Id()
    {
        return dep_Id;
    }

    public void setDep_Id(String dep_Id)
    {
        this.dep_Id = dep_Id;
    }

    public String getPos_Name()
    {
        return pos_Name;
    }

    public void setPos_Name(String pos_Name)
    {
        this.pos_Name = pos_Name;
    }

    public String getPos_Content()
    {
        return pos_Content;
    }

    public void setPos_Content(String pos_Content)
    {
        this.pos_Content = pos_Content;
    }

    public double getPos_Salary()
    {
        return pos_Salary;
    }

    public void setPos_Salary(double pos_Salary)
    {
        this.pos_Salary = pos_Salary;
    }

    public double getPos_Allowance()
    {
        return pos_Allowance;
    }

    public void setPos_Allowance(double pos_Allowance)
    {
        this.pos_Allowance = pos_Allowance;
    }

    public double getPos_Perquisites()
    {
        return pos_Perquisites;
    }

    public void setPos_Perquisites(double pos_Perquisites)
    {
        this.pos_Perquisites = pos_Perquisites;
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
