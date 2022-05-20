package com.autumn.system.convertre;

import org.springframework.core.convert.converter.Converter;

public class BooleanConverter implements Converter<String, Boolean> {
	 
	 @Override
	 public Boolean convert(String source){
	     try {
	    	 if(source==null||source.trim().equals("")){
	    		 return false;
	    	 }
	    	 source=source.trim().toLowerCase();
	    	 if(source.toLowerCase().equals("true")||source.toLowerCase().equals("yes")||source.toLowerCase().equals("y")||source.toLowerCase().equals("1")){
	    		 return true;
	    	 }else{
	    		 return false;
	    	 }
	     } catch (Exception e) {}
	     return false;
	 }
}