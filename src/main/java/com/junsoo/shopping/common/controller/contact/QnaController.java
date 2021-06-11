package com.junsoo.shopping.common.controller.contact;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.vo.QnaVO;

@RestController
public class QnaController {

	@RequestMapping(value = "/qna", method = RequestMethod.GET) 
	public ModelAndView getQna() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("contents/contact/qna_regist");
		return mv;
	}

	@RequestMapping(value = "/qna", method = RequestMethod.POST) 
	public ModelAndView postQna(HttpServletRequest request,
									@ModelAttribute QnaVO qnaVO) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
}
