package com.junsoo.shopping.common.dao.user;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	private static final String namespace = "com.mapper.userDataMapper";

	@Inject
	SqlSession sqlSession;
	
	@Override
	public void signOut(UserVO userVO) throws Exception {
		logger.info("signOut method called");
		sqlSession.update(namespace + ".signOutUser", userVO);
	}
	
	@Override
	public UserVO selectOneUser(String userId) throws Exception {
		logger.info("selectOneUser called");
		return sqlSession.selectOne(namespace + ".selectOneUser", userId);
	}

}
