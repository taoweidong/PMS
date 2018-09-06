package com.pms.entity;

public class PageBean
{

    private int page; // 第几页

    private int rows; // 每页记录数

    @SuppressWarnings("unused")
    private int start; // 起始页

    public PageBean(final int page, final int rows)
    {
        super();
        this.page = page;
        this.rows = rows;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(final int page)
    {
        this.page = page;
    }

    public int getRows()
    {
        return rows;
    }

    public void setRows(final int rows)
    {
        this.rows = rows;
    }

    public int getStart()
    {
        return (page - 1) * rows;
    }

    public void setStart(final int st)
    {
        this.start = st;
    }

}
