package com.autumn.system.entitys;
/**
 * 名   称：LogEntity.java
 * 描   述：
 * 作   者：李波
 * 时   间：Oct 30, 2017 4:36:47 PM
 * --------------------------------------------------
 * 修改历史
 * 序号    日期    修改人     修改原因 
 * 1                                                        
 * **************************************************
 */
public class LogEntity {
	private String id;
	private String operatorName;		//操作人
	private String loginName;		//操作人登录名
	private String content;		//v操作内容
	private String action;		//操作动作
	private String module;		//所属模块
	private String type;			//操作类型 1：后台。2：接口。3：手持机。4：APP
	private String operatorTime;		//操作时间
	private String sysCode;		//系统类别1云平台、2停车系统、3清分结算系统4诱导系统、5公共服务系统
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOperatorTime() {
		return operatorTime;
	}
	public void setOperatorTime(String operatorTime) {
		this.operatorTime = operatorTime;
	}
}
