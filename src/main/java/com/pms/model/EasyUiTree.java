package com.pms.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class EasyUiTree implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String text;
    private String state;
    private String checked;
    private Map<String, String> attribates;
    private List<EasyUiTree> children;

    public EasyUiTree() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public Map<String, String> getAttribates() {
        return attribates;
    }

    public void setAttribates(Map<String, String> attribates) {
        this.attribates = attribates;
    }

    public List<EasyUiTree> getChildren() {
        return children;
    }

    public void setChildren(List<EasyUiTree> children) {
        this.children = children;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public EasyUiTree(String id, String text, String state, String checked,
                      Map<String, String> attribates, List<EasyUiTree> children) {
        super();
        this.id = id;
        this.text = text;
        this.state = state;
        this.checked = checked;
        this.attribates = attribates;
        this.children = children;
    }

}
