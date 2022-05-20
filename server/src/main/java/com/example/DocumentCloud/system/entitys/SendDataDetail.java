package com.autumn.system.entitys;

/**
 * 发送数据的detail
 * {parkingId:"",data:[{arriveNumber:"来车Id",license:"车牌",licenseColor:"车牌颜色(1白  2黑  3蓝   4黄  5绿)",
 * arriveTime:"来车时间（Unix时间戳 秒）",userID:"收费员编号"},{....},...]}
 * @author Administrator
 *
 */
public class SendDataDetail {
	private String arriveNumber;
	private String license;
	private String licenseColor;
	private String arriveTime;
	private String userID;
	public String getArriveNumber() {
		return arriveNumber;
	}
	public void setArriveNumber(String arriveNumber) {
		this.arriveNumber = arriveNumber;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getLicenseColor() {
		return licenseColor;
	}
	public void setLicenseColor(String licenseColor) {
		this.licenseColor = licenseColor;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
}
