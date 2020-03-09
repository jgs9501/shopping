package com.junsoo.shopping.common.service.reigst;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.regist.UserDAO;
import com.junsoo.shopping.common.vo.regist.UserVO;
import com.junsoo.shopping.util.WebUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;
	
	private WebUtils webUtils;
	private PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public UserVO selectOneUser(int seq_user_id) throws Exception{
		
		return dao.selectOneUser(seq_user_id);
	}

	@Override
	public void insertUser(UserVO vo, HttpServletRequest request) throws Exception {
	    
		try {
			
			vo.setUser_ipaddress("127.0.0.1");
			//vo.setPassword(passwordEncoder.encode(vo.getPassword()));
			logger.info(vo.toString());
			dao.insertUser(vo);
		} catch (Exception e) {
			
			logger.error("inserUser() error : " + e.toString() + "\n" + vo.toString());
			e.printStackTrace();
		}
	}

	@Override
	public int selectCheckId(String user_id) throws Exception {
		
		return dao.selectCheckId(user_id);
	}
}
