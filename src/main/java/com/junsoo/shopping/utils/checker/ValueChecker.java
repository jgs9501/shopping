package com.junsoo.shopping.utils.checker;

public class ValueChecker {

	public boolean isCheckCategory(int category) {
		
		int[] categories = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
		for(int arg : categories) {
			if (arg == category) return true;
		}
		return false;
	}
	
	public String getCategoryName(int category) {
		
		String categoryName = "";
		switch (category) {
			case 100 : categoryName="전기/전자제품"; break;
			case 200 : categoryName="화장품/향수"; break;
			case 300 : categoryName="식료품"; break;
			case 400 : categoryName="의류"; break;
			case 500 : categoryName="신발류"; break;
			case 600 : categoryName="악세서리"; break;
			case 700 : categoryName="사무용품"; break;
			case 800 : categoryName="주방용품"; break;
			case 900 : categoryName="음반/DVD"; break;
			case 1000 : categoryName="기타"; break;
			default: categoryName="error"; break;
		}
		return categoryName;
	}
	
}
