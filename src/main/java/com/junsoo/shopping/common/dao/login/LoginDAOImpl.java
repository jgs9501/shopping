package com.junsoo.shopping.common.dao.login;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.UserVO;

@Repository
public class LoginDAOImpl implements LoginDAO{

	@Inject
	private SqlSession sqlSession;
	private static final Logger logger = LoggerFactory.getLogger(LoginDAO.class);
	private static final String namespace = "com.mapper.userDataMapper";
	
	@Override
	public String selectPassword(String userId) throws Exception {
		logger.info("selectPassword called");
		return sqlSession.selectOne(namespace + ".selectPassword", userId);
	}

	@Override
	public UserVO selectOneUser(UserVO vo) throws Exception {
		logger.info("selectOneUser called");
		return sqlSession.selectOne(namespace + ".selectOneUser", vo);
	}

}
