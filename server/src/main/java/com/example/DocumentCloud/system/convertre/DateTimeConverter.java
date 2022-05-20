package com.autumn.system.convertre;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter implements Converter<String, Date> {
	
	 @Override
	 public Date convert(String source){
	     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     try {   
	         return dateFormat.parse(source.trim());
	     } catch (Exception e) {e.printStackTrace();}
	     return null;   
	 }
}