package com.junsoo.shopping.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 메인 홈 페이지 컨트롤러 작업
 */
@Controller
public class HomeControllerImpl implements HomeController{
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/contents/login", method = RequestMethod.GET)
	public ModelAndView login() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("loginPage");
		return mv;
	}
	
}
