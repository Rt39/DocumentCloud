
package com.autumn.system.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 名   称：Serialnumber.java
 * 描   述：
 * 作   者：王飞
 * 时   间：Jun 5, 2017 4:34:52 PM
 * --------------------------------------------------
 * 修改历史
 * 序号    日期    修改人     修改原因 
 * 1                                                        
 * **************************************************
 */
public class Serialnumber {
	 /** 
     * 获取现在时间 
     * @return返回字符串格式yyyyMMddHHmmss 
     */  
      public static String getStringDate() {  
             Date currentTime = new Date();  
             SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");  
             String dateString = formatter.format(currentTime);  
             return dateString;  
          }  
      /** 
       * 由年月日时分秒+3位随机数 
       * 生成流水号 
       * @return 
       */  
      public static String Getnum(String title){  
          String t = getStringDate();  
          int x=(int)(Math.random()*900)+100;  
          String serial = t + x;  
          return title+"_"+serial;  
      }  
        
    
}
