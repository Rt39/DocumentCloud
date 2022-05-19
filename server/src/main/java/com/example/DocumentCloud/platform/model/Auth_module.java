package com.autumn.platform.model;

public class Auth_module {
    private Integer moduleid;

    private Integer parentid;

    private String modulename;

    private String path;

    private Integer ordernumber;

    private Integer syscode;

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(Integer ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Integer getSyscode() {
        return syscode;
    }

    public void setSyscode(Integer syscode) {
        this.syscode = syscode;
    }

    @Override
    public String toString() {
        return "Auth_module{" +
                "moduleid=" + moduleid +
                ", parentid=" + parentid +
                ", modulename='" + modulename + '\'' +
                ", path='" + path + '\'' +
                ", ordernumber=" + ordernumber +
                ", syscode=" + syscode +
                '}';
    }
}