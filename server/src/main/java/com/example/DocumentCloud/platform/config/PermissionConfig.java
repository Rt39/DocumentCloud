package com.autumn.platform.config;

/**
 * @ClassName: PermissionConfig
 * @author 秋雨
 * 修改历史 
 *  序号------原因------修改人---日期---
 *   1.                               
 *   2.                                
 */
public class PermissionConfig {
	public static String getPermissionTree = "SELECT"
								+"	m.moduleId as id,"
								+"	m.parentId ,"
								+"	m.moduleName as title,"
								+"	m.path as href, "
								+"	m.ordernumber as ordernumber "
								+"FROM"
								+"	auth_module AS m "
								+"ORDER BY "
								+"	m.ordernumber ";

	public static String selectPermissionTree_role = "SELECT	m.moduleId AS id,	m.parentId,	m.moduleName AS title,	m.path AS href,if(mr.id IS NULL,false,true) AS checked,m.ordernumber AS ordernumber"
			+" FROM	auth_module AS m LEFT JOIN "
			+" (SELECT * FROM "
			+" auth_modulejoinrole "
			+" WHERE roleId = ? )"
			+" AS mr ON m.moduleId = mr.moduleId "
			+" ORDER BY m.ordernumber  ";
}
