package com.junsoo.shopping.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ���� Ȩ ������ ��Ʈ�ѷ� �۾�
 */
@Controller
public class HomeController{
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String main() {
		return "/index";
	}
}
