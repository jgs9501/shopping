package com.junsoo.shopping.common.service.login;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.login.LoginDAO;
import com.junsoo.shopping.common.dao.login.LoginDAOImpl;
import com.junsoo.shopping.common.vo.UserVO;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Inject
	private LoginDAO dao;
	private static final Logger logger = LoggerFactory.getLogger(LoginDAOImpl.class);
	
	@Override
	public String selectPassword(String userId) throws Exception {
		logger.info("selectLogin called");
		return dao.selectPassword(userId);
	}

	@Override
	public UserVO selectOneUser(UserVO vo) throws Exception {
		logger.info("selectOneUser called");
		return dao.selectOneUser(vo);
	}

}
