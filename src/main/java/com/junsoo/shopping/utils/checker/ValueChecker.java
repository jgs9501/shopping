package com.junsoo.shopping.utils.checker;

public class ValueChecker {

	/**
	 * 상품 카테고리의 유무 판단
	 * 100 : 전기/전자제품
	 * 200 : 화장품/향수
	 * 300 : 식료품
	 * 400 : 의류
	 * 500 : 신발류
	 * 600 : 악세서리
	 * 700 : 사무용품
	 * 800 : 주방용품
	 * 900 : 음반/DVD
	 * 1000 : 기타
	 * 그 외 : 에러
	 * @param category
	 * @return boolean
	 */
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
	
	/**
	 * 공지사항과 자주묻는질문 종류의 존재유무 확인 ("PI", "DI", "UI", "OI", "ETC")
	 * @param type
	 * @return
	 */
	public boolean isExistContactType(String type) {
		
		String[] types = {"PI", "DI", "UI", "OI", "ETC"};
		for(String arg : types) {
			if(type.compareToIgnoreCase(arg) == 0) { return true; }
		}
		return false;
	}
	
	/**
	 * 공지사항과 자주묻는질문 종류의 이름변환 ("PI", "DI", "UI", "OI", "ETC")
	 * @param type
	 * @return
	 */
	public String getContactTypeToName(String type) {
		
		switch (type) {
			case "PI": return "상품문의";
			case "DI": return "배송문의";
			case "UI": return "회원문의";
			case "OI": return "주문/결제";
			case "ETC":	return "기타";
			default: return "기타";
		}
	}
	
}
