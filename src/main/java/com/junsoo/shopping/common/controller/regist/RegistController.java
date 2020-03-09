package com.junsoo.shopping.common.controller.regist;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.junsoo.shopping.common.service.reigst.UserService;
import com.junsoo.shopping.common.vo.regist.UserVO;

@Controller
@RequestMapping(value = "/contents")
public class RegistController {

	private static final Logger logger = LoggerFactory.getLogger(RegistController.class);
	
	@Inject
	UserService userService;
	
	@RequestMapping(value = "/regist/registPage", method = RequestMethod.GET)
	public String getRegist(){
		
		logger.info(RegistController.class + " getRegist() method called");
		return "contents/regist/registPage";
	}

	@RequestMapping(value = "/regist/registPage", method = RequestMethod.POST)
	public String postRegist(UserVO vo, HttpServletRequest request){
		
		try {
			userService.insertUser(vo, request);
			logger.info(" postRegist() method called");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "contents/regist/registComplete";
	}
	
	@RequestMapping(value = "/regist/checkId", method = RequestMethod.POST)
	public @ResponseBody int checkId(@RequestParam("user_id") String user_id) throws Exception {
		
		return userService.selectCheckId(user_id);
	}
}
