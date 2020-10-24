package com.junsoo.shopping.utils.checker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker {

	/**
	 * 
	 * @param str
	 * @return
	 */
	public boolean isUserId(String str) {
		
		String regex = "^[A-Za-z0-9]{5,20}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public boolean isPassword(String str) {
		
		String regex = "^[A-Za-z0-9!@#$%^*()\\-_=+\\\\\\|\\[\\]{};:\\'\",.<>\\/?]{8,16}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public boolean isUserName(String str) {
		
		String regex = "^[가-힣]{2,10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public boolean isDomain(String str) {
		
		String regex = "^[A-Za-z0-9_\\.\\-]+\\.[A-Za-z0-9\\-]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
	
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public boolean isBirthday(String str) {
		
		String regex = "^([0-9]{4})-?([0-9]{2})-?([0-9]{2})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public boolean isGender(String str) {
		
		String regex = "^[GM]$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public boolean isPhone(String str) {
		
		String regex = "^[0-9]{11}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public boolean isPost(int num) {
		return ((num >= 10000) && (num <= 99999)) ? true : false;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public boolean isPost(String str) {
		
		String regex = "^[A-Za-z0-9가-힣\\s]$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
}
