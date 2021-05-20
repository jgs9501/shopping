package com.junsoo.shopping.common.service.user;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.user.UserDAO;
import com.junsoo.shopping.common.dao.user.UserpointDAO;
import com.junsoo.shopping.common.vo.UserVO;
import com.junsoo.shopping.utils.WebUtils;
import com.junsoo.shopping.utils.checker.RegexChecker;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Inject
	UserDAO dao;
	
	@Inject
	UserpointDAO userPointDAO;

	@Override
	public String selectPassword(String userId) throws Exception {
		return dao.selectPassword(userId);
	}

	@Override
	public UserVO selectOneUser(UserVO userVO) throws Exception {
		try {
			if(userVO.getUser_id() == "" || userVO.getSeq_user_id() < 1) {
				logger.error("selectOneUser method : Session does not exist. Login confirmation required");
				return null;
			}
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return dao.selectOneUser(userVO);
	}
	
	@Override
	public UserVO selectOneUser(String userId) throws Exception {
		
		try {
			if(userId == "") {
				logger.error("selectOneUser : Session does not exist. Login confirmation required");
				return null;
			}
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return dao.selectOneUser(userId);
	}

	@Override
	public UserVO selectOneUser(int seq_user_id) throws Exception {
		try {
			if(seq_user_id < 1) {
				logger.error("Session does not exist. Login confirmation required");
				return null;
			}
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return dao.selectOneUser(seq_user_id);
	}
	
	@Override
	public void updateUser(UserVO userVO) throws Exception {
		
		logger.info("updateUser method called");
		dao.updateUser(userVO);
	}

	@Override
	public void updatePassword(UserVO userVO) throws Exception {
		
		logger.info("updatePassword method called");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		RegexChecker regexChecker = new RegexChecker();
		String error = "";
		
		try {
			if(!regexChecker.isUserId(userVO.getUser_id())) {
				error = "The type invalid ID.";
				logger.error(error);
			}
			if(!regexChecker.isPassword(userVO.getPassword())) {
				error = "The type invalid Password.";
				logger.error(error);
			}
			if(error == "") {
				userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
				dao.updatePassword(userVO);
			}
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	public void insertUser(UserVO userVO, HttpServletRequest request) throws Exception {
		
		logger.info("insertUser called");
		WebUtils webUtils = new WebUtils();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		RegexChecker regexChecker = new RegexChecker();
		
	    String error = "";
		try {
			// 유저 ID 정규식 확인
			if(!regexChecker.isUserId(userVO.getUser_id())) {
				error = "UserID input type error." + userVO.getUser_id();
			}
			// 유저 패스워드 정규식 확인
			else if(!regexChecker.isPassword(userVO.getPassword())) {
				error = "Password input type error." + userVO.getPassword();
			}
			// 유저 이름 정규식 확인
			else if(!regexChecker.isUserName(userVO.getUser_name())) {
				error = "User name input type error." + userVO.getUser_name();
			}
			// 유저 이메일 정규식 확인 (UserId와 형식 동일하여 UserId사용)
			else if(!regexChecker.isUserId(userVO.getUser_email())) {
				error = "User email input type error." + userVO.getUser_email();
			}
			// 유저 도메인 정규식 확인
			else if(!regexChecker.isDomain(userVO.getUser_domain())) {
				error = "User domain input type error." + userVO.getUser_domain();
			}
			// 유저 핸드폰 번호 정규식 확인
			else if(!regexChecker.isPhone(userVO.getUser_phone())) {
				error = "User phone input type error." + userVO.getUser_phone();
			}
			// 유저 우편번호 정규식 확인
			else if(!regexChecker.isPost(userVO.getUser_post())) {
				error = "User post number input type error." + userVO.getUser_post();
			}
			
			// 유저 IP주소 기입
			userVO.setUser_ipaddress(webUtils.getClientIp(request));
			// 비밀번호 암호화
			userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
			if(error == "") {
				dao.insertUser(userVO);
				userPointDAO.insertUserPoint(userVO);
			}
			else {
				logger.warn(error);
				userVO = null;
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
	public int selectCheckId(String userId) throws Exception {
		return dao.selectCheckId(userId);
	}
	
	@Override
	public void signOut(UserVO userVO) throws Exception {
		logger.info("signOut method called");
		dao.signOut(userVO);
	}

}
