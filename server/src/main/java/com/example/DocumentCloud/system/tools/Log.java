package com.autumn.system.tools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

	private static boolean bl=false;
	private static String dirpath = "/park/logs/";
	private static String dirpath2 = "/park/interfacelogs/";
	private static BufferedOutputStream output=null;
	
	/**
	 * 
	 * @param log 1:输出业务日志,0:输出地磁数据
	 * @param logType
	 */
	public static void log(String log, int logType){
		if(!bl){
			File f=new File(dirpath);
			f.mkdirs();
			bl=true;
		}
		synchronized(Log.class){
			Date d=new Date();
			SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd");
			String fileName=fm.format(d)+".out";
			
			if (logType == 1) {
                fileName=fm.format(d) + "-action" +".out";
            }
			try {
			    output=new BufferedOutputStream(new FileOutputStream(dirpath+fileName,true));
				String date=new SimpleDateFormat("HH:mm:ss").format(d);
				output.write((date+" --->> "+log+"\r\n").getBytes("utf-8"));
				output.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					output.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void logforInterface(String log){
		if(!bl){
			File f=new File(dirpath2);
			f.mkdirs();
			bl=true;
		}
		synchronized(Log.class){
			Date d=new Date();
			SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd");
			String fileName=fm.format(d)+".out";
			
			try {
			    output=new BufferedOutputStream(new FileOutputStream(dirpath2+fileName,true));
				String date=new SimpleDateFormat("HH:mm:ss").format(d);
				output.write((date+" --->> "+log+"\r\n").getBytes("utf-8"));
				output.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					output.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}