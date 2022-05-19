package com.autumn.platform.config;

/**
 * @ClassName: AuthRoleConfig
 * @Description: TODO
 * @author 秋雨
 * 修改历史 
 *  序号------原因------修改人---日期---
 *   1.                               
 *   2.                                
 */
public class AuthRoleConfig {
	public static String getAllByPage = " select r.id,r.rolename,u.cnName as addusername,r.addtime from auth_role r LEFT JOIN userinfo u ON r.adduser = u.userId #where order by addtime desc";
}
