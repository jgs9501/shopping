package com.junsoo.shopping.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * ���� Ȩ ������ ��Ʈ�ѷ� �۾�
 */
@Controller
public class HomeController{
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView redirectMain() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
}
