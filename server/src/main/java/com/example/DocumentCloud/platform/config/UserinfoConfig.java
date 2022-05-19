package com.autumn.platform.config;

/**
 * @ClassName: UserinfoConfig
 * @Description: TODO
 * @author 秋雨
 * 修改历史 
 *  序号------原因------修改人---日期---
 *   1.                               
 *   2.                                
 */
public class UserinfoConfig {
	public static String getAllByPage = " select * from userinfo where isdel = 0 #where order by addtime desc";
	public static String activeByIds = " update userinfo set is_active = 1 ";
	public static String disableByIds = " update userinfo set is_active = 0 ";
	public static String deleteByIds = " update userinfo set isdel = 1 ";
	/*获取所有角色-分配角色用*/
	public static String getAllcheckBoxRole = " SELECT r.id, r.rolename,if(ur.id IS NULL,'','checked') AS ischeck from auth_role r LEFT JOIN "
			+" (SELECT * FROM auth_userjoinrole WHERE userId =?) as ur ON r.id = ur.roleId ORDER BY r.addtime";
}
