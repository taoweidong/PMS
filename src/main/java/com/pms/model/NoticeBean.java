package com.pms.model;


import java.util.Date;


/**
 * @author Taowd 功 能：公告实体 编写时间：2017-4-8-上午10:16:36
 */
public class NoticeBean
{
    private String not_Id;

    private String not_Title;

    private String not_Content;

    private Date not_Date;

    private String not_Author;

    private String not_ext1;

    private String not_ext2;

    private String not_ext3;

    @Override
    public String toString()
    {
        return "公告实体 [not_Id=" + not_Id + ", not_Title=" + not_Title + ", not_Content="
               + not_Content + ", not_Date=" + not_Date + ", not_Author=" + not_Author
               + ", not_ext1=" + not_ext1 + ", not_ext2=" + not_ext2 + ", not_ext3=" + not_ext3
               + "]";
    }

    public NoticeBean()
    {
        super();
    }

    public NoticeBean(String not_Id, String not_Title, String not_Content, Date not_Date,
                      String not_Author, String not_ext1, String not_ext2, String not_ext3)
    {
        super();
        this.not_Id = not_Id;
        this.not_Title = not_Title;
        this.not_Content = not_Content;
        this.not_Date = not_Date;
        this.not_Author = not_Author;
        this.not_ext1 = not_ext1;
        this.not_ext2 = not_ext2;
        this.not_ext3 = not_ext3;
    }

    public String getNot_Id()
    {
        return not_Id;
    }

    public void setNot_Id(String not_Id)
    {
        this.not_Id = not_Id;
    }

    public String getNot_Title()
    {
        return not_Title;
    }

    public void setNot_Title(String not_Title)
    {
        this.not_Title = not_Title;
    }

    public String getNot_Content()
    {
        return not_Content;
    }

    public void setNot_Content(String not_Content)
    {
        this.not_Content = not_Content;
    }

    public Date getNot_Date()
    {
        return not_Date;
    }

    public void setNot_Date(Date not_Date)
    {
        this.not_Date = not_Date;
    }

    public String getNot_Author()
    {
        return not_Author;
    }

    public void setNot_Author(String not_Author)
    {
        this.not_Author = not_Author;
    }

    public String getNot_ext1()
    {
        return not_ext1;
    }

    public void setNot_ext1(String not_ext1)
    {
        this.not_ext1 = not_ext1;
    }

    public String getNot_ext2()
    {
        return not_ext2;
    }

    public void setNot_ext2(String not_ext2)
    {
        this.not_ext2 = not_ext2;
    }

    public String getNot_ext3()
    {
        return not_ext3;
    }

    public void setNot_ext3(String not_ext3)
    {
        this.not_ext3 = not_ext3;
    }

}
