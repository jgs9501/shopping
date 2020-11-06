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
	public String selectPassword(String userId) throws Exception {
		logger.info("selectPassword called");
		return sqlSession.selectOne(namespace + ".selectPassword", userId);
	}

	@Override
	public UserVO selectOneUser(UserVO userVO) throws Exception {
		logger.info("selectOneUser called");
		return sqlSession.selectOne(namespace + ".selectOneUser", userVO);
	}
	
	@Override
	public UserVO selectOneUser(String userId) throws Exception {
		logger.info("selectOneUser method called");
		return sqlSession.selectOne(namespace + ".selectOneUser", userId);
	}

	@Override
	public void updateUser(UserVO userVO) throws Exception {
		logger.info("updateUser method called");
		sqlSession.update(namespace + ".updateUser", userVO);
	}

	@Override
	public void updatePassword(UserVO userVO) throws Exception {
		logger.info("updatePassword method called");
		sqlSession.update(namespace + ".updatePassword", userVO);
	}
	
	@Override
	public void insertUser(UserVO userVO) throws Exception {
		logger.info("insertUser called");
		sqlSession.insert(namespace + ".insertUser", userVO);
	}

	@Override
	public int selectCheckId(String userId) throws Exception {
		logger.info("selectCheckId called");
		return sqlSession.selectOne(namespace + ".selectCheckId", userId);
	}
	
	@Override
	public void signOut(UserVO userVO) throws Exception {
		logger.info("signOut method called");
		sqlSession.update(namespace + ".signOutUser", userVO);
	}

}
