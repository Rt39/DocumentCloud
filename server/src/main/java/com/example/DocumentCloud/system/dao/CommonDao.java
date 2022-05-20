package com.autumn.system.dao;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.autumn.system.entitys.PageList;

@Repository("commonDao")
public class CommonDao {
    @Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

    /**
	 * 查询返回实体对象集合
	 * @param sql    sql语句
	 * @param params 填充sql问号占位符
	 * @param cla    实体对象元类型
	 * @return
	 */
	public List queryForObjectList(String sql,Object[] params,final Class cla){
	    final List list=new ArrayList();
	    try{
	    	jdbcTemplate.query(sql, params, new RowCallbackHandler(){
                @Override
                public void processRow(ResultSet rs) {
                	try{
                		List<String> columnNames=new ArrayList<String>();
                    	ResultSetMetaData meta=rs.getMetaData();
                    	int num=meta.getColumnCount();
                    	for(int i=0;i<num;i++){
                    		columnNames.add(meta.getColumnLabel(i+1).toLowerCase().trim());
                    	}
                    	/*获取所有字段包含父类*/
                    	List<String> fields = getFieldList(cla);
		            	/*获取所有字段(不包含父类)
		            	List<String> fields=new ArrayList<String>();
		            	Field[] f = cla.getDeclaredFields();
		            	for (int i = 0; i < f.length; i++) {
							fields.add(f[i].getName());
						}*/
                        do{
                        	Object obj=null;
                        	try{
                        		obj=cla.getConstructor().newInstance();
                        	}catch(Exception ex){
                        		ex.printStackTrace();
                        	}
                        	for(int i=0;i<num;i++){
                        		Object objval=rs.getObject(i+1);
                        		for(int n=0;n<fields.size();n++){
                        			String fieldName=fields.get(n).trim();
                        			if(columnNames.get(i).toLowerCase().replace("_", "").equals(fieldName.toLowerCase().replace("_", "").trim())){
                        				BeanUtils.copyProperty(obj, fieldName, objval);
                        				break;
                        			}
                        		}
                        	}
                        	list.add(obj);
                        }while(rs.next());
                	}catch(Exception ex){
                		ex.printStackTrace();
                	}
                }
	    	});
	    }catch(Exception ex){ex.printStackTrace();}
	    if(list.size()<=0){
	    	return null;
	    }
	    return list;
	}
	
	/**
	 * 查询返回实体Bean
	 * @param sql  sql语句
	 * @param params  设置sql语句中的问号占位符
	 * @param cla  要返回的Bean元类型
	 * @return
	 */
	public Object queryForObject(String sql,Object[] params,final Class cla){
	    try{
	    	final Object obj=cla.getDeclaredConstructor().newInstance();
	    	jdbcTemplate.query(sql, params, new RowCallbackHandler(){
	            @Override
                public void processRow(ResultSet rs) {
	            	try{
	            		List<String> columnNames=new ArrayList<String>();
		            	ResultSetMetaData meta=rs.getMetaData();
		            	int num=meta.getColumnCount();
		            	for(int i=0;i<num;i++){
		            		columnNames.add(meta.getColumnLabel(i+1).toLowerCase().trim());
		            	}
						/*获取所有字段*/
						List<String> fields = getFieldList(cla);
		            	for(int i=0;i<num;i++){
		            		Object objval=rs.getObject(i+1);
		            		for(int n=0;n<fields.size();n++){
		            			String fieldName=fields.get(n).trim();
								if(columnNames.get(i).toLowerCase().replace("_", "").equals(fieldName.toLowerCase().replace("_", "").trim())){
		            				BeanUtils.copyProperty(obj, fieldName, objval);
		            				break;
		            			}
		            		}
		            	}
	            	}catch(Exception ex){
	            		ex.printStackTrace();
	            	}
	            }
		    });
		    return obj;
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	    return null;
	}
	
	/**
	 * 只查询一列基础数据类型对象。适用于只有一行查询结果的数据
	 * @param sql
	 * @param params
	 * @param cla Integer.class,Float.class,Double.Class,Long.class,Boolean.class,Char.class,Byte.class,Short.class
	 * @return
	 */
	public Object queryOneColumnForSigetonRow(String sql,Object[] params,Class cla){
		Object result=null;
		try{
			if(params==null||params.length>0){
				result=jdbcTemplate.queryForObject(sql,params,cla);
			}else{
				result=jdbcTemplate.queryForObject(sql,cla);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询返回基础数据类型集合。只查询一列，但是存在多行数据的情况
	 * @param sql
	 * @param params
	 * @param cla Integer.class,Float.class,Double.Class,Long.class,Boolean.class,Char.class,Byte.class,Short.class
	 * @return
	 */
	public List  queryOneColumnForMoreRows(String sql,Object[] params,Class cla){
		try{
			if(params!=null&&params.length>0){
				return jdbcTemplate.queryForList(sql, params, cla);
			}else{
				return jdbcTemplate.queryForList(sql, cla);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询返回Map<String,Object>格式数据,每一个Map代表一行数据，列名为key
	 * @param sql  sql语句
	 * @param params 填充问号占位符数组
	 * @return
	 */
	public Map<String,Object> queryForMap(String sql,Object[] params){
		try{
			if(params!=null&&params.length>0){
				return jdbcTemplate.queryForMap(sql,params);
			}
			return jdbcTemplate.queryForMap(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询返回List<Map<String,Object>>格式数据,每一个Map代表一行数据，列名为key
	 * @param sql  sql语句
	 * @param params 填充问号占位符数组
	 * @return
	 */
	public List<Map<String,Object>> queryForMaps(String sql,Object[] params){
		try{
			if(params!=null&&params.length>0){
				return jdbcTemplate.queryForList(sql, params);
			}
			return jdbcTemplate.queryForList(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 插入一行数据返回新插入行自增主键
	 * @param sql  sql语句
	 * @param primaryKeyName   主键名称
	 * @param params 填充问号占位符数组
	 * @return
	 */
	public long insertReturnNewColumnValues(final String sql,final Object[] params,final String primaryKeyName) {  
        long result=0;
        try{
        	KeyHolder keyHolder = new GeneratedKeyHolder();  
        	jdbcTemplate.update(new PreparedStatementCreator() {  
                                    @Override
                                    public PreparedStatement createPreparedStatement(Connection connection){
                                        try{
                                        	PreparedStatement ps = connection.prepareStatement(sql,new String[]{primaryKeyName});  
                                            if(params!=null&&params.length>0){
                                            	for(int i=0;i<params.length;i++){
                                            		ps.setObject(i+1, params[i]);
                                            	}
                                            }
                                            return ps;  
                                        }catch(Exception ex){ex.printStackTrace();}
                                    	return null;
                                    }
                                },keyHolder);
            List<Map<String,Object>> list=keyHolder.getKeyList();
            if(list!=null&&list.size()>0){
        		Map<String,Object> map=list.get(0);
        		Iterator<String> it=map.keySet().iterator();
        		while(it.hasNext()){
        			String key=it.next();
        			result=Long.parseLong(map.get(key).toString().trim());
        			break;
        		}
            }
        }catch(Exception ex){
        	ex.printStackTrace();
        }
        return result;
    }

	/**
	 * 增加，修改，删除(支持blob类型操作)
	 * @param sql  sql语句
	 * @param params   填充问号占位符数组,参数中存在File类型时自动以blob处理
	 * @return 是否执行成功
	 */
	public boolean execute(String sql,final Object[] params){
		int result=0;
		try{
			if(params!=null&&params.length>0){
				boolean bl=false;
				for(int i=0;i<params.length;i++){
					if(params[i] instanceof File){
						bl=true;
						break;
					}
				}
				if(!bl){
					result = jdbcTemplate.update(sql, new PreparedStatementSetter(){
					      @Override
                          public void setValues(PreparedStatement statement){
					    	  int count= 0;
					    	  try{
						    	  for(int i=0;i<params.length;i++){
						    		  statement.setObject(i+1,params[i]);
						    		  count++;
						    	  }
					    	  }catch(Exception ex){
				    			  ex.printStackTrace();
				    		  }
					 }});
				}else{
					final LobHandler lobHandler=new DefaultLobHandler();
					result=jdbcTemplate.execute(sql,new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
							@Override
                            protected void setValues(PreparedStatement pstmt, LobCreator lobCreator){
								try{
									for(int i=0;i<params.length;i++){
										if(params[i] instanceof File){
											File file=(File)params[i];
											try{
												InputStream is=new FileInputStream(file);
												lobCreator.setBlobAsBinaryStream(pstmt,i+1,is,(int)file.length());
											}catch(Exception ex){ex.printStackTrace();}
										}else{
											pstmt.setObject(i+1, params[i]);
										}
									}
								}catch(Exception ex){ex.printStackTrace();}
							}
						});
				}
			}else{
				result=jdbcTemplate.update(sql);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result>0;
	}
	
	
	/**
	 * 增加，修改，删除(支持blob类型操作)(抛出异常)
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            填充问号占位符数据,参数中存在File类型时自动以blob处理
	 * @return 是否执行成功
	 */
	public boolean execute2(String sql, final Object[] params) throws Exception {
		int result = 0;

		if (params != null && params.length > 0) {
			boolean bl = false;
			for (int i = 0; i < params.length; i++) {
				if (params[i] instanceof File) {
					bl = true;
					break;
				}
			}
			if (!bl) {
				result = jdbcTemplate.update(sql,
						new PreparedStatementSetter() {
							@Override
                            public void setValues(PreparedStatement statement)
									throws SQLException {
								int count = 0;
								for (int i = 0; i < params.length; i++) {
									statement.setObject(i + 1, params[i]);
									count++;
								}
							}
						});
			} else {
				final LobHandler lobHandler = new DefaultLobHandler();
				result = jdbcTemplate.execute(sql,
						new AbstractLobCreatingPreparedStatementCallback(
								lobHandler) {
							@Override
                            protected void setValues(PreparedStatement pstmt,
                                                     LobCreator lobCreator) throws SQLException {
								for (int i = 0; i < params.length; i++) {
									if (params[i] instanceof File) {
										File file = (File) params[i];
										try {
											InputStream is = new FileInputStream(
													file);
											lobCreator.setBlobAsBinaryStream(
													pstmt, i + 1, is,
													(int) file.length());
										} catch (Exception ex) {
											ex.printStackTrace();
										}
									} else {
										pstmt.setObject(i + 1, params[i]);
									}
								}
							}
						});
			}
		} else {
			result = jdbcTemplate.update(sql);
		}
		return result > 0;
	}
	
	/**
	 * 执行多条SQL语句
	 * @param sqls  List sql语句
	 * @return 是否执行成功
	 */
	public void execute3(List<String> sqls){
		for(int i=0;i<sqls.size();i++){
			try{
				jdbcTemplate.update(sqls.get(i));
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 查询单行blob类型列字段集合
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<byte[]> selectBlobsByOneRow(String sql,Object[] params){
		final List<byte[]> list=new ArrayList<byte[]>();
		final LobHandler lobHandler=new DefaultLobHandler();
		jdbcTemplate.query(sql,params,new AbstractLobStreamingResultSetExtractor(){
	        @Override
            protected void streamData(ResultSet rs){
	        	try{
	        		ResultSetMetaData meta=rs.getMetaData();
	            	int num=meta.getColumnCount();
            		for(int i=0;i<num;i++){
            			byte[] data=lobHandler.getBlobAsBytes(rs, i+1);
            			list.add(data);
            		}
	        	}catch(Exception ex){ex.printStackTrace();}
	        }
		});
		return list;
	}
	
	
	
	/**
	 * 批量处理
	 * @param sql
	 * @param params   填充问号的占位符，此参数不能为空
	 * @return  批处理每一条语句的执行结果
	 */
	public int[] executeBatch(String sql,final List<Object[]> params) {
		int[] updateCounts=null;
		try{
			updateCounts = jdbcTemplate.batchUpdate(sql,
	                 new BatchPreparedStatementSetter(){
	                        @Override
                            public void setValues(PreparedStatement ps, int i) {
	                        	int count = 0;
	                        	try {
		                        	for(int n=0;n<params.get(i).length;n++){
										ps.setObject(n+1, params.get(i)[n]);
										count++;
		                        	}
	                        	} catch (SQLException e) {
									e.printStackTrace();
								}
	                        }
	                        @Override
                            public int getBatchSize() {
	                            return params.size();
	                        }
	                }
	         );
		}catch(Exception ex){
			ex.printStackTrace();
		}
        return updateCounts;
    }
	
	
	/**
	 * 批量处理
	 * @param sql
	 * @param params   填充问号的占位符，此参数不能为空
	 * @return  批处理每一条语句的执行结果
	 */
	public int[] executeBatch2(String sql,final List<Object[]> params) throws Exception  {
		int[] updateCounts=null;
		updateCounts = jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter(){
                       @Override
                       public void setValues(PreparedStatement ps, int i) throws SQLException {
                       	int count = 0;
                       	for(int n=0;n<params.get(i).length;n++){
							ps.setObject(n+1, params.get(i)[n]);
							count++;
                    	}
                       }
                       @Override
                       public int getBatchSize() {
                           return params.size();
                       }
               }
        );
        return updateCounts;
    }

	/**
	 * 查询分页（MySQL数据库）
	 * @param sql     最终执行查询的语句
	 * @param params  填充sql语句中的问号占位符数据
	 * @param page    想要第几页的数据
	 * @param pagerow 每页显示多少条数据
	 * @param cla     要封装成的实体元类型
	 * @return        pageList对象
	 */
	public PageList queryByPageForMySQL(String sql, Object[] params, int page, int pagerow,Class cla) {
		String rowsql="select count(*) from ("+sql+") gmtxtabs_";
		int pages = 0;
        int rows=(Integer)queryOneColumnForSigetonRow(rowsql, params, Integer.class);
        if (rows % pagerow == 0) {
            pages = rows / pagerow;
        } else {
            pages = rows / pagerow + 1;
        }
        if(page<=1){
            sql+=" limit 0,"+pagerow;
        }else{
            sql+=" limit "+((page-1)*pagerow)+","+pagerow;
        }
        PageList pl =new PageList();
        List list=null;
        if(cla!=null){
        	list=queryForObjectList(sql, params, cla);
        }else{
        	list=queryForMaps(sql, params);
        }
        pl.setPage(page);
		pl.setPages(pages);
		pl.setList(list);
		pl.setTotalRows(rows);
		return pl;
	}
	
	
	public PageList queryByPageForMySQLBigData(String sql,String sql2, Object[] params, Object[] params2, int page, int pagerow,Class cla) {
		
		int pages = 0;
        int rows=(Integer)queryOneColumnForSigetonRow(sql, params, Integer.class);
        if (rows % pagerow == 0) {
            pages = rows / pagerow;
        } else {
            pages = rows / pagerow + 1;
        }
        if(page<=1){
            sql2+=" limit 0,"+pagerow;
        }else{
            sql2+=" limit "+((page-1)*pagerow)+","+pagerow;
        }
        PageList pl =new PageList();
        List list=null;
        if(cla!=null){
        	list=queryForObjectList(sql2, params2, cla);
        }else{
        	list=queryForMaps(sql2, params2);
        }
        pl.setPage(page);
		pl.setPages(pages);
		pl.setList(list);
		pl.setTotalRows(rows);
		return pl;
	}
	
	/**
	 * 查询分页（SQLServer数据库）
	 * @param sql     最终执行查询的语句
	 * @param params  填充sql语句中的问号占位符数据
	 * @param page    想要第几页的数据
	 * @param pagerow 每页显示多少条数据
	 * @param cla     要封装成的实体元类型
	 * @param isdesc  是否降序
	 * @return        pageList对象
	 */
	public PageList queryByPageForSQLServer(String sql, Object[] params, int page, int pagerow,Class cla,boolean isdesc) {
	    sql=sql.trim().toLowerCase();
	    String rowsql="select count(*) from(select top 100 percent " + sql.substring(sql.indexOf("select") + 7, sql.length() - (sql.indexOf("select") + 7)) + " ) gmtxapages_";
	    int pages = 0;
        int rows=(Integer)queryOneColumnForSigetonRow(rowsql, params, Integer.class);
        if (rows % pagerow == 0) {
            pages = rows / pagerow;
        } else {
            pages = rows / pagerow + 1;
        }
	    String columnsql = sql.substring(sql.indexOf("select") + 7, sql.indexOf("from") - (sql.indexOf("select") + 7));
        int pp = page == 1 ? pagerow : (page - 1) * pagerow;
        String sql2 = "select  top " + pp + " " + sql.substring(sql.indexOf("select") + 7, sql.length() - (sql.indexOf("select") + 7));
        sql = "select  top 100 percent " + sql.substring(sql.indexOf("select") + 7, sql.length() - (sql.indexOf("select") + 7));
        String[] cs = columnsql.split(",");
        String cid = cs[0].trim();
        if (cid.indexOf(" as ") > 0)
        {
            cid = cid.substring(cid.indexOf(" as ") + 3).trim();
        }
        else if (cid.indexOf(" ") > 0)
        {
            cid = cid.substring(cid.indexOf(" ")).trim();
        }
        else if (cid.indexOf(".") > 0)
        {
            cid = cid.substring(cid.indexOf(".") + 1).trim();
        }
        String selectsql = "";
        if (!isdesc)
        {//升序方式分页
            if (page == 1)
            {
                selectsql = sql2;
            }
            else
            {
                selectsql = "select top " + pagerow + " * from (" + sql + ") as splitTable_ where splitTable_." + cid + ">(select max(splitTable3_." + cid + ") from (" + sql2 + ") as splitTable3_) order by splitTable_." + cid + " asc";
            }
        }
        else
        {//降序方式分页
            if (page == 1)
            {
                selectsql = sql2;
            }
            else
            {
                selectsql = "select top " + pagerow + " * from (" + sql + ") as splitTable_ where splitTable_." + cid + "<(select min(splitTable3_." + cid + ") from (" + sql2 + ") as splitTable3_) order by splitTable_." + cid + " desc";
            }
        }
        PageList pl=new PageList();
        List list=null;
        if(cla!=null){
        	list=queryForObjectList(selectsql, params, cla);
        }else{
        	list=queryForMaps(selectsql, params);
        }
        pl.setPage(page);
		pl.setPages(pages);
		pl.setList(list);
		pl.setTotalRows(rows);
		return pl;
	}
	
	/**
	 * 查询分页（ORACLE数据库）
	 * @param sql     最终执行查询的语句
	 * @param params  填充sql语句中的问号占位符数据
	 * @param page    想要第几页的数据
	 * @param pagerow 每页显示多少条数据
	 * @param cla     要封装成的实体元类型
	 * @return        pageList对象
	 */
	public PageList queryByPageForOracle(String sql, Object[] params, int page, int pagerow,Class cla) {
		String rowsql="select count(*) from ("+sql+") gmtxtabs_";
		int pages = 0;
        int rows=(Integer)queryOneColumnForSigetonRow(rowsql, params, Integer.class);
        if (rows % pagerow == 0) {
            pages = rows / pagerow;
        } else {
            pages = rows / pagerow + 1;
        }
        sql="select * from (select gmtxtabs_.*,rownum rn_ from("+sql+") gmtxtabs_ where ROWNUM <= "+page*pagerow+" ) where rn_>"+(page-1)*pagerow;
        PageList pl =new PageList();
        List list=null;
        if(cla!=null){
        	list=queryForObjectList(sql, params, cla);
        }else{
        	list=queryForMaps(sql, params);
        }
        pl.setPage(page);
		pl.setPages(pages);
		pl.setList(list);
		pl.setTotalRows(rows);
		return pl;
	}


	/**
	 * 调用存储过程(无返回值)
	 * TODO
	 * @param procedureName 存储过程名
	 * @param paramList 参数列表
	 */
	public void callProcedure(String procedureName,List paramList){
		String callProcedureString = "call "+procedureName+"(";
		if (paramList!= null && paramList.size()>0) {
			String paramString = "";
			for (int i = 0; i < paramList.size(); i++) {
				paramString+="'"+paramList.get(i)+"'";
				if (i<paramList.size()-1) {
					paramString += ",";
				}
			}
			callProcedureString+=paramString;
		}
		callProcedureString = callProcedureString+")";
		this.jdbcTemplate.execute(callProcedureString);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 获取类及父类的所有字段
	 * @param clz class类
	 * @return clz及其父类的所有字段
	 */
	public static List<String> getFieldList(Class clz){
		List<Field> fieldList = new ArrayList<Field>();
		List<String> fields=new ArrayList<String>();
		while (clz != null) {   //当父类为null的时候说明到达了最上层的父类(Object类).
		      fieldList.addAll(Arrays.asList(clz .getDeclaredFields()));  //获取当前类所有字段,并放入到列表
		      clz = clz.getSuperclass(); //得到父类,然后赋给自己
		}
		for (int i = 0; i < fieldList.size(); i++) {
			fields.add(fieldList.get(i).getName());
		}
		return fields;
	}
}