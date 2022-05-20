package com.autumn.system.entitys;

public class UserInfo {

	private int userId;
	private String userCode;
	private String userName;
	private String cnName;//姓名
    private String pwd;
    private String phone;
    private String email;
    private String remark;
    private String roleIds="";
    private String roleText="";
    private String captcha;  //验证码
    private String proCode;		//如果是物业用户登录，此字段有值
    
    private String sysCode;
    private String operatorID;
    private String operatorName;
    
    private String newpwd;

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleId) {
		this.roleIds+= roleId;
	}

	public String getRoleText() {
		return roleText;
	}

	public void setRoleText(String roleText) {
		this.roleText += roleText;
	}

	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
}