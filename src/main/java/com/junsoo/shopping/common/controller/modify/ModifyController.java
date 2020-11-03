package com.junsoo.shopping.common.controller.modify;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.service.modify.ModifyService;
import com.junsoo.shopping.common.vo.UserVO;

@Controller
@RequestMapping(value = "/contents")
public class ModifyController {

	@Inject
	private ModifyService modifyService;
	private static final Logger logger = LoggerFactory.getLogger(ModifyController.class);
	
	@RequestMapping(value = "/modify/modifyForm", method = RequestMethod.GET)
	public ModelAndView getModify(HttpServletRequest request) throws Exception{
		
		logger.info("getModify called");
		ModelAndView mv = new ModelAndView();
		String user = (String)request.getSession().getAttribute("user");
		
		try {
			if(user == null) {
				mv.setViewName("contents/errorForm");
			}
			else {
				mv.addObject("userVO", modifyService.selectOneUser(user));
				mv.setViewName("contents/modify/modifyForm");
			}
		} catch (NullPointerException npe) {
			logger.warn(npe.getMessage());
			mv.setViewName("/:redirect/contents/errorForm");
		} catch (Exception e) {
			logger.warn(e.getMessage());
			mv.setViewName("/:redirect/contents/errorForm");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/modify/modifyForm", method = RequestMethod.POST)
	public String postModify(UserVO userVO) throws Exception{
		
		String page = "contents/modify/modifyComplete";
		try {
			logger.info("postModify method called");
			modifyService.updateUser(userVO);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			page = "redirect:/";
		} catch (Exception e) {
			logger.error(e.getMessage());
			page = "redirect:/";
		}
		
		return page;
	}
	
	@RequestMapping(value = "/modify/modifyPassword", method = RequestMethod.GET)
	public ModelAndView getModifyPassword(HttpServletRequest request) throws Exception{
		
		logger.info("getModifyPassword called");
		ModelAndView mv = new ModelAndView();
		String user = (String)request.getSession().getAttribute("user");
		try {
			if(user == null) {
				mv.setViewName("contents/errorForm");
			}
			else {
				mv.addObject("userVO", modifyService.selectOneUser(user));
				mv.setViewName("contents/modify/modifyPassword");
			}
		} catch (NullPointerException npe) {
			logger.warn(npe.getMessage());
			mv.setViewName("/:redirect/contents/errorForm");
		} catch (Exception e) {
			logger.warn(e.getMessage());
			mv.setViewName("/:redirect/contents/errorForm");
		}
		return mv;
	}
	@RequestMapping(value = "/modify/modifyPassword", method = RequestMethod.POST)
	public String postModifyPassword(UserVO userVO, HttpServletRequest request) throws Exception{
		
		String page = "contents/modify/modifyComplete";
		try {
			logger.info("postModifyPassword method called");
			modifyService.updatePassword(userVO, request);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			page = "redirect:/";
		} catch (Exception e) {
			logger.error(e.getMessage());
			page = "redirect:/";
		}
		
		return page;
	}
	
}
