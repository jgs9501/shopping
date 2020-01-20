package com.junsoo.shopping.common.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/contents")
public class LoginControllerImpl implements LoginController {

	@Override
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("loginPage");
		return mv;
	}

}
