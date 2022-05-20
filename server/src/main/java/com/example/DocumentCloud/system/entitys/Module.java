package com.autumn.system.entitys;

import java.util.ArrayList;
import java.util.List;

public class Module{

	private int moduleId;
	private int parentId;
	private String moduleName;
	private String path;
	private String iconCls;  	//树形图标
	private String sysCode;		//所属系统
	
	private int ordernumber;
	
	private String  parentModuleName;
	
	private boolean checked=false;
	private List<Module> children=new ArrayList<Module>();

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getModuleId() {
		return moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public String getPath() {
		return path;
	}

	public List<Module> getChildren() {
		return children;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setChildren(List<Module> children) {
		this.children = children;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}

	public int getOrdernumber() {
		return ordernumber;
	}

	public void setParentModuleName(String parentModuleName) {
		this.parentModuleName = parentModuleName;
	}

	public String getParentModuleName() {
		return parentModuleName;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
}