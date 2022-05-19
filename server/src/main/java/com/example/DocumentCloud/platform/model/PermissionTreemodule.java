package com.autumn.platform.model;

import java.util.ArrayList;
import java.util.List;

public class PermissionTreemodule {

    private String title;
    private Integer id;
    private String field;
    private String href;
    private Boolean checked;
    private Boolean spread;
    private Boolean disabled;
    private List<PermissionTreemodule> children = new ArrayList<PermissionTreemodule>();

    private Integer parentId;
    private String ordernumber;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public List<PermissionTreemodule> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionTreemodule> children) {
        this.children = children;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }
}
