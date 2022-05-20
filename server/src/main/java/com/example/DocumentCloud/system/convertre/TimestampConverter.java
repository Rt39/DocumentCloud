package com.autumn.system.convertre;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 時間轉時間戳
 */
public class TimestampConverter implements Converter<String, Timestamp> {

	@Override
    public Timestamp convert(String timeStr) {
		Timestamp t = null;
        if (timeStr!=null && timeStr != "") {
        	 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        	 Date date = null;
			try {
				date = format.parse(timeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        	//日期转时间戳（毫秒）
        	t = new Timestamp(date.getTime());
        }
        return t;
	}
}