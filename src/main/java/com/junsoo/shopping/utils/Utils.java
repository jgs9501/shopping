package com.junsoo.shopping.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Utils {
	
	public String getCurrentDate() {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String date = sdf.format(cal.getTime());
		return date;
	}
	
	public String getOrderId() {
		
		String orderId = getCurrentDate();
		Random random = new Random();
		
		while(orderId.length() < 16) {
			// 대문자 영어와 숫자 반복
			if(random.nextBoolean()) {
				orderId += String.valueOf((char) ((int) (random.nextInt(26)) + 65));
			} 
			else {
				orderId += String.valueOf(random.nextInt(10));
			}
		}

		return orderId;
	}
	
	public String getOrderDetailId() {
		
		String orderDetailId = "";
		Random random = new Random();
		
		while(orderDetailId.length() < 16) {
			// 대문자 영어와 숫자 반복
			if(random.nextBoolean()) {
				orderDetailId += String.valueOf((char) ((int) (random.nextInt(26)) + 65));
			} 
			else {
				orderDetailId += String.valueOf(random.nextInt(10));
			}
		}
		
		return orderDetailId;
	}
}
