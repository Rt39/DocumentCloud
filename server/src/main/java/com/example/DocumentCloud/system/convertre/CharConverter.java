package com.autumn.system.convertre;


import org.springframework.core.convert.converter.Converter;
 
public class CharConverter implements Converter<String, Character> {  
	
	 @Override
     public Character convert(String source){
	     try {   
	    	 source=source.trim();
	         return source.trim().charAt(0); 
	     } catch (Exception e) {}          
	     return null;   
	 }
}
