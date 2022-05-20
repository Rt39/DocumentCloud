package com.autumn.system.convertre;

import java.text.NumberFormat;

import org.springframework.core.convert.converter.Converter;

public class NumberConverter implements Converter<String, Number> {  
	 
	 @Override
     public Number convert(String source){
	     try {   
	         return NumberFormat.getInstance().parse(source.trim()); 
	     } catch (Exception e) {}          
	     return null;   
	 }
}
