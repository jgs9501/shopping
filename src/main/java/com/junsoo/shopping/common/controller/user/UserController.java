package com.junsoo.shopping.common.controller.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.service.user.UserService;
import com.junsoo.shopping.common.vo.UserVO;

@Controller
@RequestMapping(value = "/contents")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Inject
	UserService userService;
	
	// 회원탈퇴 페이지 이동
	@RequestMapping(value = "/user/signOut", method = RequestMethod.GET)
	public ModelAndView getSignOut(HttpServletRequest request) throws Exception {
		
		logger.info("getSignOut method called");
		ModelAndView mv = new ModelAndView();
		String user = (String)request.getSession().getAttribute("user");
		
		try {
			mv.setViewName("contents/user/signOut");
			mv.addObject("userVO", userService.selectOneUser(user));
		} catch (Exception e) {
			logger.error(e.getMessage());
			mv.setViewName("contents/errorForm");
		}
		return mv;
	}
	
	// 회원탈퇴 기능
	@RequestMapping(value = "/user/signOut", method = RequestMethod.POST)
	public ModelAndView postSignOut(UserVO userVO, 
									HttpServletRequest request, 
									HttpSession session) throws Exception {
		
		logger.info("postSignOut method called");
		ModelAndView mv = new ModelAndView();
		
		try {
			if(userVO != null) {
				mv.addObject("result", "회원탈퇴");
				mv.setViewName("contents/complete");
				userVO = userService.selectOneUser(
						(String)request.getSession().getAttribute("user"));
				userService.signOut(userVO);
				session.invalidate();
			}
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			mv.setViewName("contents/errorForm");
		} catch (Exception e) {
			logger.error(e.getMessage());
			mv.setViewName("contents/errorForm");
		}
		return mv;
	}
}
