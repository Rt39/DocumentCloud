package com.autumn.system.convertre;

import org.springframework.core.convert.converter.Converter;
 
public class DoubleConverter implements Converter<String, Double> {
	
	 @Override
     public Double convert(String source){
	     try {
	         return Double.parseDouble(source.trim());
	     } catch (Exception e) {}
	     return null;
	 }
}
