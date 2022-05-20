package com.autumn.system.tools;
public enum CN_MessageEnum {

	InitialValue(0, "初始化值"), 
	
	AddSuccess(1, "添加成功！"),   
	
	AddFailed(2, "添加失败！"),
	
	ModifySuccess(4,"编辑成功！"),
	
	ModifyFailed(5,"编辑失败！"),
	
	DeleteSuccess(6,"删除成功"),
	
	DeleteFailed(7,"删除失败！"),

	
//	ModifySuccess(5,"修改成功！"),
//	
//	ModifyFailed(6,"修改失败！"),
	
	SameDate_DailyNews(8, "发布日期已存在！"),
	
	ResetPasswordSuccess(9,"重置密码成功！"),
	
	ResetPasswordFailed(10,"重置密码失败！"),
	
	OperatSuccess(11,"操作成功！"),
	
	OperatFailed(12,"操作失败！"),
	
	ExistSameModuleName(13, "已存在相同模块名称"),
	
	JudgeNoExit(15,"记录不存在"),
	
	JudgeExit(16,"记录存在,无需再次输入数据"),
	
	OutDate(17,"该记录已不是当天记录，清理缓存！");
	
	private int _value;
	private String _name;

	private CN_MessageEnum(int value, String name) {
		_value = value;
		_name = name;
	}

	public int value() {
		return _value;
	}

	public String getName() {
		return _name;
	}

	public static String getName(int index) {
		for (CN_MessageEnum c : CN_MessageEnum.values()) {
			if (c.value() == index) {
				return c._name;
			}
		}
		return null;
	}
}
