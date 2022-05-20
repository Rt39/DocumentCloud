package com.autumn.system.convertre;

import org.springframework.core.convert.converter.Converter;
 
public class FloatConverter implements Converter<String, Float> {  
	
	 @Override
     public Float convert(String source){
	     try {   
	         return Float.parseFloat(source.trim()); 
	     } catch (Exception e) {}          
	     return null;   
	 }
}
