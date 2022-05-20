package com.autumn.system.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;


public class CommonTool {
	/**
	   * 获取当前时间
	   * 
	   * @return yyyy-MM-dd HH:mm:ss
	   */
	public static String getNowDateStr() {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String dateString = formatter.format(currentTime);
	   return dateString;
	}
	
	/**
	   * 获取当前时间
	   * 
	   * @return yyyy-MM-dd
	   */
	public static String getNowDateStr2() {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	   String dateString = formatter.format(currentTime);
	   return dateString;
	}
	/**
	   * 获取当前时间
	   * 
	   * @return yyyy-MM-dd
	   */
	public static String getNowDateStr3() {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
	   String dateString = formatter.format(currentTime);
	   return dateString;
	}
	/**
	   * 获取当前时间
	   * 
	   * @return yyyy
	   */
	public static String getNowDateStr4() {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
	   String dateString = formatter.format(currentTime);
	   return dateString;
	}
	/** 
     * 得到几天前的时间 
     *  
     * @param d 
     * @param day 
     * @return yyyy-MM-dd
     */  
    public static String getDateBefore(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);  
        
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
 	   String dateString = formatter.format(now.getTime());
        
        return dateString;  
    }    
    /** 
     * 得到几天后的时间 
     *  
     * @param d 
     * @param day 
     * @return yyyy-MM-dd
     */  
    public static String getDateAfter(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  	   	String dateString = formatter.format(now.getTime());
        
        return dateString;  
    }
    /** 
     * 得到几年前的时间 
     *  
     * @param d 
     * @param day 
     * @return yyyy-MM-dd
     */  
    public static String getYearBefore(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.add(Calendar.YEAR, -day);
        
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
 	   String dateString = formatter.format(now.getTime());
        
        return dateString;  
    }    
    public static String formatStr(String str)
    {
    	if(str.indexOf("'") < 0)
    	{
    		return "'" + str + "'";
    	}
    	else
    	{
    		return str;
    	}
    }
    
    /**
     * 获取当年某月份的最大值 存在bug,勿用
     * 如需使用使用LastDay(int year , int month)
     * @param month
     * @return
     * ----------------------------------------
     */
    @Deprecated
    public static int LastDay(int month) { 
    	Calendar calendar = Calendar.getInstance(); 
        calendar.clear(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.MONTH, month-1); 
        int maxday =calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
        return maxday; 
	 } 
    
    /**
     * 获取某年某月的日期最大值
     * @param year 年份
     * @param month 月份
     * @return 最大值
     */
    public static int LastDay(int year , int month) { 
    	Calendar calendar = Calendar.getInstance(); 
    	calendar.clear();  //清除所有日期数据
    	if (year != 0 ) {
            calendar.set(Calendar.YEAR, year); 
		}
        calendar.set(Calendar.MONTH, month-1); 
        int maxday =calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
        return maxday; 
	 } 
	  
	 public static int FirstDay(int month) { 
		 Calendar calendar = Calendar.getInstance(); 
		 calendar.set(Calendar.MONTH, month-1); 
		 int minday =calendar.getActualMinimum(Calendar.DAY_OF_MONTH); 
		 return minday; 
	  
	 }
	 
	 /** 
	     * 时间戳转换成日期格式字符串 
	     * @param seconds 精确到秒的字符串 
	     * @param formatStr 
	     * @return 
	     */  
	    public static String timeStamp2Date(String seconds,String format) {  
	        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
	            return "";  
	        }  
	        if(format == null || format.isEmpty()) format = "yyyy-MM-dd";  
	        SimpleDateFormat sdf = new SimpleDateFormat(format);  
	        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
	    } 
	    
	    /** 
	     * 日期格式字符串转换成时间戳 
	     * @param date 字符串日期 
	     * @param format 如：yyyy-MM-dd HH:mm:ss 
	     * @return 
	     */  
	    public static String date2TimeStamp(String date_str,String format){  
	        try {  
	            SimpleDateFormat sdf = new SimpleDateFormat(format);  
	            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return "";  
	    } 
	    
	    /** 
	     * 日期格式字符串转换成时间戳 
	     * @param date 字符串日期 
	     * @param format 如：yyyy-MM-dd HH:mm:ss 
	     * @return 
	     */  
	    public static long date2TimeStamp1(String date_str,String format){  
	        try {  
	            SimpleDateFormat sdf = new SimpleDateFormat(format);  
	            return sdf.parse(date_str).getTime()/1000;  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        return 0;
	    } 
	    
	    
	    //毫秒转时间
	    	public static String changetimelong(long timemiao){
	    		String mtime = "00:00";
	    		mtime = getTimeHours(timemiao) + ":" + getTimeMinute(timemiao);
	    		return mtime;
	    	}
	    	
	    	//秒转时间
	    	public String changetimeint(int timemiao){
	    		String mtime = "00:00";
	    		mtime = getTimeHours(timemiao) + ":" + getTimeMinute(timemiao);
	    		return mtime;
	    	}
	    	private static String getTimeMinute(long time) {
	    		String mtime = null;
	    		int emptime = (int) (time / 60 % 60);
	    		if (emptime < 10) {
	    			mtime = "0" + Integer.toString(emptime);
	    		} else {
	    			mtime = Integer.toString(emptime);
	    		}
	    		return mtime;
	    	}

	    	private static String getTimeHours(long time) {
	    		String mtime = null;
	    		int emptime = (int) (time / 3600);
	    		if (emptime < 10) {
	    			mtime = "0" + Integer.toString(emptime);
	    		} else {
	    			mtime = Integer.toString(emptime);
	    		}
	    		return mtime;
	    	}
	    	
	    	public static String getProNameByCode(String proCode)
	    	{
	    		Map<String, String> map = new HashMap<String, String>();
	    		map.put("10001", "扬州市中房物业发展有限公司");
	    		map.put("fywy10006", "扬州市丰源物业服务有限公司");
	    		map.put("klwy10002", "扬州市康乐物业管理有限公司");
	    		map.put("20001", "停车公司自主收费");
	    		
	    		if(proCode==null || proCode.isEmpty())
	    			return "";
	    		else
	    			return map.get(proCode);
	    	}
	    	 public String create_nonce_str() {  
	             String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
	             String res = "";  
	             for (int i = 0; i < 16; i++) {  
	                 Random rd = new Random();  
	                 res += chars.charAt(rd.nextInt(chars.length() - 1));  
	             }  
	             return res;  
	         }  
	      /** 
	          * 构造签名 
	          * @param params 
	          * @param encode 
	          * @return 
	          * @throws UnsupportedEncodingException  
	          */  
	         public String createSign(Map<String, String> params, boolean encode) throws UnsupportedEncodingException {  
	             Set<String> keysSet = params.keySet();  
	             Object[] keys = keysSet.toArray();  
	             Arrays.sort(keys);  
	             StringBuffer temp = new StringBuffer();  
	             boolean first = true;  
	             for (Object key : keys) {  
	                 if (key == null) // 参数为空参与签名  
	                    continue;  
	                 if (first) {  
	                     first = false;  
	                 } else {  
	                     temp.append("&");  
	                 }  
	                 temp.append(key).append("=");  
	                 Object value = params.get(key);  
	                 String valueString = "";  
	                 if (null != value) {  
	                     valueString = value.toString();  
	                 }  
	                 if (encode) {  
	                     temp.append(URLEncoder.encode(valueString, "UTF-8"));  
	                 } else {  
	                     temp.append(valueString);  
	                 }  
	             }  
	             return temp.toString();  
	         }  
	         public String getSign(Map<String, String> params, String paternerKey) throws UnsupportedEncodingException {  
	             String string1 =createSign(params, false);  
	             String stringSignTemp = string1 + "&key=" + paternerKey;  
	             String signValue = DigestUtils.md5Hex(stringSignTemp).toUpperCase();  
	             return signValue;  
	         } 
	         /** 
	          * map转成xml 
	          *  
	          * @param arr 
	          * @return 
	          */  
	         public String ArrayToXml(Map<String, String> parm) {  
	             StringBuffer strbuff = new StringBuffer("<xml>");  
	             if (parm != null && !parm.isEmpty()) {  
	                 for (Entry<String, String> entry : parm.entrySet()) {  
	                     strbuff.append("<").append(entry.getKey()).append(">");
	                     strbuff.append(entry.getValue());
	                     strbuff.append("</").append(entry.getKey()).append(">");  
	                 }  
	             }  
	             return strbuff.append("</xml>").toString();  
	         }  
	         
	         
	     	/**
	         * 获取当月的 天数
	         */
	     	public static int getCurrentMonthDay() {
	     		Calendar a = Calendar.getInstance();
	     		a.set(Calendar.DATE, 1);
	            a.roll(Calendar.DATE, -1);
	            int maxDate = a.get(Calendar.DATE);
	            return maxDate;
	     	}
	     	
	        /**
	         * 根据年月获取当月最后一天
	         * @param yearmonth yyyy-MM
	         * @return yyyy-MM-dd
	         * @throws ParseException
	         */
	        public static String getLastDayOfMonth(String yearmonth) {
	            try {
	               SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
	                Date dd = format.parse(yearmonth);
	                Calendar cal = Calendar.getInstance();
	                cal.setTime(dd);
	                int cc=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	                String result = yearmonth+"-"+cc;
	                return result;
	           } catch (ParseException e) {
	               e.printStackTrace();
	           }
	           return null;
	        }

	/**
	 * 根据文件流判断图片类型
	 * @param fis
	 * @return jpg/png/gif/bmp
	 */
	public static boolean getPicType(InputStream fis) {
		//读取文件的前几个字节来判断图片格式
		byte[] b = new byte[4];
		try {
			fis.read(b, 0, b.length);
			String type = bytesToHexString(b).toUpperCase();
			if (type.contains("FFD8FF")) {
				return true; //jpg
			} else if (type.contains("89504E47")) {
				return true; //png
			} else if (type.contains("47494638")) {
				return true; //gif
			} else if (type.contains("424D")) {
				return true; //bmp
			}else{
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	/**
	 * byte数组转换成16进制字符串
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src){
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}


	/**
	 * 处理文件上传
	 */
	public static boolean handler(MultipartFile file, HttpServletResponse response,
								  Map<String, Object> result, String folder, String attachPath) throws IOException {
		String oldFileName = file.getOriginalFilename();
		String ext = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
		oldFileName = oldFileName.substring(0, oldFileName.lastIndexOf("."));
		String fileName = getNowDateStr2() + "_" + oldFileName +"."+ ext.toLowerCase();
		if (file == null || file.isEmpty()) {// step-2 判断file
			return getError("文件内容为空", HttpStatus.UNPROCESSABLE_ENTITY, result,
					response, null);
		}
		String orgFileName = file.getOriginalFilename();
		orgFileName = (orgFileName == null) ? "" : orgFileName;
		Pattern p = Pattern.compile("\\s|\t|\r|\n");
		Matcher m = p.matcher(orgFileName);
		orgFileName = m.replaceAll("_");
		String realFilePath = folder + File.separator;
		if (!(new File(realFilePath).exists())) {
			new File(realFilePath).mkdirs();
		}
		String bigRealFilePath = realFilePath
				+ File.separator+fileName;

		if (file.getSize() > 0) {
			File targetFile = new File(bigRealFilePath);
			file.transferTo(targetFile);// 写入目标文件
		}

		result.put("targetFilePath", bigRealFilePath);
		result.put("webFilePath", attachPath+File.separator+fileName);

		return true;
	}
	public static boolean getError(String message, HttpStatus status,
								   Map<String, Object> result, HttpServletResponse response,
								   Exception ex) {
		response.setStatus(status.value());
		result.put("message", message);
		return false;
	}
}
