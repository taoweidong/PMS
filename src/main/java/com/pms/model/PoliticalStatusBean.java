package com.pms.model;

/**
 * @author 陶伟东 功能：政治面貌实体类 编写时间：下午2:15:18
 */
public class PoliticalStatusBean
{
    private String ps_type;

    private String ps_name;

    private String ext1;

    private String ext2;

    private String ext3;

    public String getPs_type()
    {
        return ps_type;
    }

    public PoliticalStatusBean()
    {
        super();
    }

    public PoliticalStatusBean(String ps_type, String ps_name, String ext1, String ext2,
                               String ext3)
    {
        super();
        this.ps_type = ps_type;
        this.ps_name = ps_name;
        this.ext1 = ext1;
        this.ext2 = ext2;
        this.ext3 = ext3;
    }

    public void setPs_type(String ps_type)
    {
        this.ps_type = ps_type;
    }

    public String getPs_name()
    {
        return ps_name;
    }

    public void setPs_name(String ps_name)
    {
        this.ps_name = ps_name;
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
