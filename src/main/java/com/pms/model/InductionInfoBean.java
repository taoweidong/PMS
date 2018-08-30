package com.pms.model;

/**
 * @author Taowd 功 能：员工的入职信息实体，职工的岗位信息等。 编写时间：2017-4-8-上午10:29:59
 */
public class InductionInfoBean
{
    private String ind_Id;

    private String emp_No;

    private String pos_Id;

    private String pos_Name;

    public String getPos_Name()
    {
        return pos_Name;
    }

    public void setPos_Name(String pos_Name)
    {
        this.pos_Name = pos_Name;
    }

    private String ind_Date;

    private String ind_State;

    private String ind_Enddate;

    private String ind_Reasons;

    private String ext1;

    private String ext2;

    private String ext3;

    @Override
    public String toString()
    {
        return "入职信息实体 [ind_Id=" + ind_Id + ", emp_No=" + emp_No + ", pos_Id=" + pos_Id
               + ", ind_Date=" + ind_Date + ", ind_State=" + ind_State + ", ind_Enddate="
               + ind_Enddate + ", ind_Reasons=" + ind_Reasons + ", ext1=" + ext1 + ", ext2=" + ext2
               + ", ext3=" + ext3 + "]";
    }

    public InductionInfoBean()
    {
        super();
    }

    public InductionInfoBean(String ind_Id, String emp_No, String pos_Id, String ind_Date,
                             String ind_State, String ind_Enddate, String ind_Reasons, String ext1,
                             String ext2, String ext3)
    {
        super();
        this.ind_Id = ind_Id;
        this.emp_No = emp_No;
        this.pos_Id = pos_Id;
        this.ind_Date = ind_Date;
        this.ind_State = ind_State;
        this.ind_Enddate = ind_Enddate;
        this.ind_Reasons = ind_Reasons;
        this.ext1 = ext1;
        this.ext2 = ext2;
        this.ext3 = ext3;
    }

    public String getInd_Id()
    {
        return ind_Id;
    }

    public void setInd_Id(String ind_Id)
    {
        this.ind_Id = ind_Id;
    }

    public String getEmp_No()
    {
        return emp_No;
    }

    public void setEmp_No(String emp_No)
    {
        this.emp_No = emp_No;
    }

    public String getPos_Id()
    {
        return pos_Id;
    }

    public void setPos_Id(String pos_Id)
    {
        this.pos_Id = pos_Id;
    }

    public String getInd_Date()
    {
        return ind_Date;
    }

    public void setInd_Date(String ind_Date)
    {
        this.ind_Date = ind_Date;
    }

    public String getInd_State()
    {
        return ind_State;
    }

    public void setInd_State(String ind_State)
    {
        this.ind_State = ind_State;
    }

    public String getInd_Enddate()
    {
        return ind_Enddate;
    }

    public void setInd_Enddate(String ind_Enddate)
    {
        this.ind_Enddate = ind_Enddate;
    }

    public String getInd_Reasons()
    {
        return ind_Reasons;
    }

    public void setInd_Reasons(String ind_Reasons)
    {
        this.ind_Reasons = ind_Reasons;
    }

    public String getExt1()
    {
        return ext1;
    }

    /**
     * Author:Taowd 功能：审批状态 开发日期：2017-4-17-下午1:20:00
     * 
     * @param ext1
     */
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
