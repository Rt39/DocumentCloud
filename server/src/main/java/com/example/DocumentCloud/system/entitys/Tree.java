package com.autumn.system.entitys;

import java.util.ArrayList;
import java.util.List;

public class Tree {
	
	private String id;

	private String pId;

	private String name;

	private boolean isParent;

	private boolean open;

	private boolean checked;

	private boolean nocheck;
	
	private String istype;

	private List<Tree> children;
	
	private String appversionId;

	public String getAppversionId() {
		return appversionId;
	}

	public void setAppversionId(String appversionId) {
		this.appversionId = appversionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getParent() {
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

	public List<Tree> getChildren() {
		if (children==null) {
			children = new ArrayList<Tree>();
			return children;
		}
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}
	

	private String attributes;

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getAttributes() {
		return attributes;
	}

	public String getIstype() {
		return istype;
	}

	public void setIstype(String istype) {
		this.istype = istype;
	}

	
	

}
