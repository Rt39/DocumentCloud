package com.autumn.system.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ValidationUtil {

	/**
	 * 验证整数
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumber(String str) {
		Pattern p = Pattern.compile("^-?\\d+");
		Matcher m = p.matcher(str);
		return (m.matches());
	}

	/**
	 * 验证Email
	 * 
	 * @param str
	 * @return
	 */
	public boolean isEmail(String str) {
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = p.matcher(str);
		return (m.matches());
	}

	/**
	 * 验证电话号码
	 * 
	 * @param str
	 * @return
	 */
	public boolean isPhone(String str) {
		Pattern p = Pattern.compile("^((\\d{3,4})|\\d{3,4}-)?\\d{7,8}$");
		Matcher m = p.matcher(str);
		return (m.matches());
	}

	/**
	 * 验证邮编
	 * 
	 * @param str
	 * @return
	 */
	public boolean isCode(String str) {
		Pattern p = Pattern.compile("^[1-9]\\d{5}$");
		Matcher m = p.matcher(str);
		return (m.matches());
	}

	/**
	 * 验证手机或电话号码
	 * 
	 * @param str
	 * @return
	 */
	public boolean isPhone_OR_Mobile(String str) {
		Pattern p = Pattern
				.compile("^((\\d{3,4})|\\d{3,4}-)?\\d{7,8}$|(^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\\d{8}$)");
		Matcher m = p.matcher(str);
		return (m.matches());
	}

	/**
	 * 验证手机号码
	 * 
	 * @param str
	 * @return
	 */
	public boolean isMobile(String str) {
		Pattern p = Pattern
				.compile("^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\\d{8}$");
		Matcher m = p.matcher(str);
		return (m.matches());
	}

	/**
	 * 验证身份证号
	 * 
	 * @param str
	 * @return
	 */
	public boolean isPepoNumber(String str) {
		Pattern p = Pattern.compile("\\d{15}|\\d{18}|\\d{17}X|\\d{17}x");
		Matcher m = p.matcher(str);
		return (m.matches());
	}

	/**
	 * 验证浮点数
	 * 
	 * @param str
	 * @return
	 */
	public boolean isFDNumber(String str) {
		Pattern p = Pattern
				.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$");
		Matcher m = p.matcher(str);
		return (m.matches());
	}

	/**
	 * 验证QQ
	 * 
	 * @param str
	 * @return
	 */
	public boolean isQQ(String str) {
		Pattern p = Pattern.compile("[1-9][0-9]{4,10}");
		Matcher m = p.matcher(str);
		return (m.matches());
	}

	/**
	 * 获得包含中英文字符的字符串长度
	 * 
	 * @param str
	 * @return
	 */
	public int getStrLength(String str) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		for (int i = 0; i < str.length(); i++) {
			String temp = str.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength;
	}

	/**
	 * 验证最大数值
	 * 
	 * @param str
	 * @param d
	 * @return
	 */
	public boolean validaterMaxValue(String str, double d) {
		double dd = Double.valueOf(str.trim());
		if (dd > d) {
			return false;
		}
		return true;
	}

	/**
	 * 验证最小数值
	 * 
	 * @param str
	 * @param d
	 * @return
	 */
	public boolean validaterMinValue(String str, double d) {
		double dd = Double.valueOf(str.trim());
		if (dd < d) {
			return false;
		}
		return true;
	}

	public static String getStr(String str) {
		if (null == str || str.trim().equals("")) {
			return str;
		}
		str = str.replaceAll("\"", "“").replaceAll(":", ":")
				.replaceAll("'", "‘").replaceAll("<", "《").replaceAll(">", "》")
				.replaceAll("=", "");
		return str;
	}
	
	//.replaceAll("&", "&amp;")  李波注释：当界面有&时，数据库里面会替换成&amp;
	
}