package com.junsoo.shopping.common.service.user;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.user.UserDAO;
import com.junsoo.shopping.common.vo.UserVO;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Inject
	UserDAO dao;

	@Override
	public void signOut(UserVO userVO) throws Exception {
		logger.info("signOut method called");
		dao.signOut(userVO);
	}

	@Override
	public UserVO selectOneUser(String userId) throws Exception {
		logger.info("selectOneUser called");
		return dao.selectOneUser(userId);
	}
	
}
