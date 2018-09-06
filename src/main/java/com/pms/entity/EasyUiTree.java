package com.pms.entity;


import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class EasyUiTree implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String id;

    private String text;

    private String state;

    private String checked;

    private Map<String, String> attribates;

    private List<EasyUiTree> children;

    public EasyUiTree()
    {
        super();
    }

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(final String text)
    {
        this.text = text;
    }

    public String getState()
    {
        return state;
    }

    public void setState(final String state)
    {
        this.state = state;
    }

    public String getChecked()
    {
        return checked;
    }

    public void setChecked(final String checked)
    {
        this.checked = checked;
    }

    public Map<String, String> getAttribates()
    {
        return attribates;
    }

    public void setAttribates(final Map<String, String> attribates)
    {
        this.attribates = attribates;
    }

    public List<EasyUiTree> getChildren()
    {
        return children;
    }

    public void setChildren(final List<EasyUiTree> children)
    {
        this.children = children;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    public EasyUiTree(final String id, final String text, final String state, final String checked,
                      final Map<String, String> attribates, final List<EasyUiTree> children)
    {
        super();
        this.id = id;
        this.text = text;
        this.state = state;
        this.checked = checked;
        this.attribates = attribates;
        this.children = children;
    }

}
