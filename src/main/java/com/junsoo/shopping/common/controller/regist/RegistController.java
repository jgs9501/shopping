package com.junsoo.shopping.common.controller.regist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/contents")
public class RegistController {

	@RequestMapping(value = "/regist/registPage", method = RequestMethod.GET)
	public ModelAndView regist(){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("contents/regist/registPage");
		return mv;
		
	}
}
