package com.autumn.system.tools;

import java.util.UUID;

/**
 * �? 称：UUIDGenerator.java �? 述： �? 者：李波 �? 间：Mar 13, 2015 9:15:21 AM
 * -------------------------------------------------- 修改历史 序号 日期 修改�? 修改原因 1
 * 名 称：UUIDGenerator.java 
 * 描 述： 
 * 作 者：李波 
 * 时 间：Mar 13, 2015 9:15:21 AM
 * -------------------------------------------------- 
 * 修改历史 序号 日期  修改原因 

 * **************************************************
 */
public class UUIDGenerator {
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
