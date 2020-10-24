package com.junsoo.shopping.common.service.reigster;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.register.RegisterDAO;
import com.junsoo.shopping.common.vo.UserVO;
import com.junsoo.shopping.utils.WebUtils;
import com.junsoo.shopping.utils.checker.RegexChecker;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

	private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);
	
	@Inject
	private RegisterDAO dao;
	
	@Override
	public void insertUser(UserVO vo, HttpServletRequest request) throws Exception {
		
		logger.info("insertUser called");
		WebUtils webUtils = new WebUtils();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		RegexChecker regexChecker = new RegexChecker();
		
	    String error = "";
		try {
			// 유저 ID 정규식 확인
			if(!regexChecker.isUserId(vo.getUser_id())) {
				error = "UserID input type error." + vo.getUser_id();
			}
			// 유저 패스워드 정규식 확인
			else if(!regexChecker.isPassword(vo.getPassword())) {
				error = "Password input type error." + vo.getPassword();
			}
			// 유저 이름 정규식 확인
			else if(!regexChecker.isUserName(vo.getUser_name())) {
				error = "User name input type error." + vo.getUser_name();
			}
			// 유저 이메일 정규식 확인 (UserId와 형식 동일하여 UserId사용)
			else if(!regexChecker.isUserId(vo.getUser_email())) {
				error = "User email input type error." + vo.getUser_email();
			}
			// 유저 도메인 정규식 확인
			else if(!regexChecker.isDomain(vo.getUser_domain())) {
				error = "User domain input type error." + vo.getUser_domain();
			}
			// 유저 핸드폰 번호 정규식 확인
			else if(!regexChecker.isPhone(vo.getUser_phone())) {
				error = "User phone input type error." + vo.getUser_phone();
			}
			// 유저 우편번호 정규식 확인
			else if(!regexChecker.isPost(vo.getUser_post())) {
				error = "User post number input type error." + vo.getUser_post();
			}
			
			// 유저 IP주소 기입
			vo.setUser_ipaddress(webUtils.getClientIp(request));
			// 비밀번호 암호화
			vo.setPassword(passwordEncoder.encode(vo.getPassword()));
			if(error == "") {
				dao.insertUser(vo);
			}
			else {
				logger.warn(error);
				vo = null;
			}
			
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage() + error);
			throw npe;
		} catch (DataIntegrityViolationException dive) {
			logger.error(dive.getMessage() + error);
			throw dive;
		} catch (SQLIntegrityConstraintViolationException sql) {
			logger.error(sql.getMessage() + error);
			throw sql;
		}
		
	}
	

	@Override
	public int selectCheckId(String user_id) throws Exception {
		return dao.selectCheckId(user_id);
	}
}
