package com.autumn.system.entitys;

/**
 * 角色
 */
public class RoleEntity {
	
	private int id;
    private String rolename;
    private int adduser;
    private String addtime;
    private String cnName;
    private String syscode;		//所属系统
	public String getSyscode() {
		return syscode;
	}

	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}

	public int getId() {
		return id;
	}
	
	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}


	public String getRolename() {
		return rolename;
	}

	public int getAdduser() {
		return adduser;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public void setAdduser(int adduser) {
		this.adduser = adduser;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
}