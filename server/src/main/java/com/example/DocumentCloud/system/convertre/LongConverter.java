package com.autumn.system.convertre;

import org.springframework.core.convert.converter.Converter;
 
public class LongConverter implements Converter<String, Long> {  
	
	 @Override
     public Long convert(String source){
	     try {   
	         return Long.parseLong(source.trim()); 
	     } catch (Exception e) {}          
	     return null;   
	 }
}
