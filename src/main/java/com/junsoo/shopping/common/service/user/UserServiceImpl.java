package com.junsoo.shopping.common.service.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.user.UserDAO;
import com.junsoo.shopping.common.dao.user.UserpointDAO;
import com.junsoo.shopping.common.vo.SecurityUserVO;
import com.junsoo.shopping.common.vo.StoreVO;
import com.junsoo.shopping.common.vo.UserVO;
import com.junsoo.shopping.utils.WebUtils;
import com.junsoo.shopping.utils.checker.RegexChecker;
import com.junsoo.shopping.utils.checker.SecurityAuthorities;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private static final int ROLE_STORE = 2;
	@Inject
	private UserDAO userDAO;
	
	@Inject
	private UserpointDAO userPointDAO;

	@Override
	public String selectPassword(String userId) throws Exception {
		return userDAO.selectPassword(userId);
	}

	@Override
	public UserVO selectOneUser(UserVO userVO) throws Exception {
		try {
			if(userVO.getUser_id() == "" || userVO.getSeq_user_id() < 1) {
				logger.error("selectOneUser method : Session does not exist. Login confirmation required");
				return null;
			}
			return userDAO.selectOneUser(userVO);
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			logger.error(npe.getMessage());
			throw npe;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public UserVO selectOneUser(String userId) throws Exception {
		
		try {
			if(userId == "") {
				logger.error("selectOneUser : Session does not exist. Login confirmation required");
				return null;
			}
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			logger.error(npe.getMessage());
			throw npe;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return userDAO.selectOneUser(userId);
	}

	@Override
	public UserVO selectOneUser(int seq_user_id) throws Exception {
		try {
			if(seq_user_id < 1) {
				logger.error("Session does not exist. Login confirmation required");
				return null;
			}
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			logger.error(npe.getMessage());
			throw npe;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return userDAO.selectOneUser(seq_user_id);
	}
	
	@Override
	public void updateUser(UserVO userVO) throws Exception {
		
		logger.info("updateUser method called");
		userDAO.updateUser(userVO);
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
				userDAO.updatePassword(userVO);
			}
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			logger.error(npe.getMessage());
			throw npe;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
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
			else if(!regexChecker.isUserEmail(userVO.getUser_email())) {
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
				userDAO.insertUser(userVO);
				userPointDAO.insertUserPoint(userVO);
			}
			else {
				logger.warn(error);
				userVO = null;
			}
			
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage() + error);
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
	}
	

	@Override
	public int selectCheckId(String userId) throws Exception {
		return userDAO.selectCheckId(userId);
	}
	
	@Override
	public void signOut(UserVO userVO) throws Exception {
		logger.info("signOut method called");
		userDAO.signOut(userVO);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserVO userVO = new UserVO();
		try {
			userVO = userDAO.selectOneUser(username);
			if(userVO == null) {
				throw new UsernameNotFoundException("loadUserByUsername() userVO is empty.");
			}
			// Spring security의 UserDetails
			SecurityUserVO securityUserVO = new SecurityUserVO(userVO);
			return securityUserVO;
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (UsernameNotFoundException unfe) {
			logger.error(unfe.getMessage());
			unfe.printStackTrace();
			throw unfe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new Error(e.getMessage());
		}
	}

	@Override
	public void insertUserToStoreAuth(StoreVO storeVO) throws Exception {
		
		SecurityAuthorities securityAuthorities = new SecurityAuthorities();
		UserVO userVO = new UserVO();
		String username = securityAuthorities.getUsername();
		int seq_user_id = 0;
		
		try {
			userVO = userDAO.selectOneUser(username);
			seq_user_id = userVO.getSeq_user_id();
			if(seq_user_id < 1) {
				logger.error("inserUserToStoreAuth is error : " + userVO);
				throw new ValidationException("UserVO seq_user_id is invalid : " + userVO);
			}
			storeVO.setSeq_user_id(seq_user_id);
			userDAO.insertUserToStoreAuth(storeVO);
			userVO.setAuth(ROLE_STORE);
			userDAO.updateUser(userVO);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (UsernameNotFoundException unfe) {
			logger.error(unfe.getMessage());
			unfe.printStackTrace();
			throw unfe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new Error(e.getMessage());
		}
	}
}
