package com.junsoo.shopping.common.controller.user;

import java.util.Random;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.junsoo.shopping.common.service.user.UserService;
import com.junsoo.shopping.common.vo.UserVO;

@RestController
public class MailController {

	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@Inject
	JavaMailSender javaMailSender;
	@Inject
	UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/checkMail", method = RequestMethod.POST)
	public String postCheckMail(@RequestParam String mail, 
								@RequestParam String user_id) throws Exception {
		
		Random random=new Random();  //난수 생성을 위한 랜덤 클래스
		String key="";  //인증번호 
		UserVO userVO;
		String db_mail = "";
		try {
			// 가입된 유저의 이메일 비교확인
			userVO = userService.selectOneUser(user_id);
			db_mail = userVO.getUser_email() + "@" + userVO.getUser_domain();
			if(!db_mail.equals(mail)) {
				return "";
			}
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(mail);
			
			int numIndex=random.nextInt(999999)+100000; //6자리 랜덤 정수를 생성
			key+=numIndex;
			message.setSubject("JS Shop 인증번호 입니다.");
			message.setText("안녕하세요. JS Shop을 이용해주셔서 감사합니다.\n"
					      + user_id + " 님의 비밀번호 변경 인증번호입니다.\n"
					      + "아래의 인증번호를 입력해주시길 바랍니다.\n\n"
					      + key);
			javaMailSender.send(message);
			
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return key;
	}
}
