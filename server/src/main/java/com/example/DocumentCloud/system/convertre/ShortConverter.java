package com.autumn.system.convertre;

import org.springframework.core.convert.converter.Converter;

public class ShortConverter implements Converter<String, Short> {  
	 
	 @Override
     public Short convert(String source){
	     try {   
	         return Short.parseShort(source.trim()); 
	     } catch (Exception e) {}          
	     return null;   
	 }
}
