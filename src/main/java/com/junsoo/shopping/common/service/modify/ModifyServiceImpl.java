package com.junsoo.shopping.common.service.modify;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.modify.ModifyDAO;
import com.junsoo.shopping.common.vo.UserVO;
import com.junsoo.shopping.utils.checker.RegexChecker;

@Service
@Transactional
public class ModifyServiceImpl implements ModifyService {

	@Inject
	private ModifyDAO dao;
	private static final Logger logger = LoggerFactory.getLogger(ModifyService.class);
	
	@Override
	public UserVO selectOneUser(String userId) throws Exception {
		
		logger.info("selectOneUser method called");
		return dao.selectOneUser(userId);
	}

	@Override
	public void updateUser(UserVO userVO) throws Exception {
		
		logger.info("updateUser method called");
		dao.updateUser(userVO);
	}

	@Override
	public void updatePassword(UserVO userVO, HttpServletRequest request) throws Exception {
		
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

}
