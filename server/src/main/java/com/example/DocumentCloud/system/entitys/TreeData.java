package com.autumn.system.entitys;

import java.util.List;

/**
 * 名      称 :TreeData.java 
 * @author    蒋芒芒
 * 创 建 时 间 :Dec 22, 2016 3:53:51 PM 
 */
public class TreeData {
	private String id;

	private String text;

	private String pid;
	
	private List<TreeData> children;
	
	private boolean isParent;

	private boolean open;

	private boolean checked;

	private boolean nocheck;
	
	private String attributes;

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public List<TreeData> getChildren() {
		return children;
	}

	public void setChildren(List<TreeData> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
