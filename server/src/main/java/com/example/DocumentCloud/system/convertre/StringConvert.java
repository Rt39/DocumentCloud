package com.autumn.system.convertre;

import org.springframework.core.convert.converter.Converter;

import com.autumn.system.tools.ValidationUtil;

public class StringConvert implements Converter<String, String> {
	 @Override
     public String convert(String source){
    	 if(source==null||source.trim().equals("")){
    		 return "";
    	 }
    	 source=ValidationUtil.getStr(source.trim());	  
	     return source;
	 }
}
