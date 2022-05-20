package com.autumn.system.convertre;

import org.springframework.core.convert.converter.Converter;
 
public class IntegerConverter implements Converter<String, Integer> {  
	
	 @Override
     public Integer convert(String source){
	     try {   
	         return Integer.parseInt(source.trim()); 
	     } catch (Exception e) {}
	     return null;   
	 }
}
