package com.junsoo.shopping.common.dao.modify;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.UserVO;

@Repository
public class ModifyDAOImpl implements ModifyDAO {

	@Inject
	private SqlSession sqlSession;
	private static final Logger logger = LoggerFactory.getLogger(ModifyDAO.class);
	private static final String namespace = "com.mapper.userDataMapper";
	
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

	
}
