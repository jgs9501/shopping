package com.junsoo.shopping.utils.checker;

public class ValueChecker {

	public boolean isCheckCategory(int category) {
		
		int[] categories = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
		for(int arg : categories) {
			if (arg == category) return true;
		}
		return false;
	}
}
