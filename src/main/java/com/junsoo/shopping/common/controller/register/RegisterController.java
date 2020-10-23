package com.junsoo.shopping.common.controller.register;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.junsoo.shopping.common.service.reigster.RegisterService;
import com.junsoo.shopping.common.vo.UserVO;

@Controller
@RequestMapping(value = "/contents")
public class RegisterController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Inject
	RegisterService userService;
	
	@RequestMapping(value = "/regist/registPage", method = RequestMethod.GET)
	public String getRegister(){
		
		logger.info(RegisterController.class + " getRegist() method called");
		return "contents/regist/registPage";
	}

	@RequestMapping(value = "/regist/registPage", method = RequestMethod.POST)
	public String postRegister(UserVO vo, HttpServletRequest request) throws Exception{
		
		String page = "contents/regist/registComplete";
		try {
			logger.info("postRegist() method called");
			userService.insertUser(vo, request);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			page = "redirect:/regist/registPage";
		}
		
		return page;
	}
	
	@RequestMapping(value = "/regist/checkId", method = RequestMethod.POST)
	public @ResponseBody int checkId(@RequestParam("user_id") String user_id) throws Exception {
		
		return userService.selectCheckId(user_id);
	}
}
