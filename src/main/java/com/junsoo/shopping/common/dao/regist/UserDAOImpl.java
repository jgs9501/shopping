package com.junsoo.shopping.common.dao.regist;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.regist.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	private static final String namespace = "com.mapper.userDataMapper";
	
	@Override
	public UserVO selectOneUser(int seq_user_id) throws Exception {
		return sqlSession.selectOne(namespace + ".selectOneUser", seq_user_id);
	}

	@Override
	public void insertUser(UserVO vo) throws Exception {
		logger.info("insertUser called");
		sqlSession.insert(namespace + ".insertUser", vo);
	}

	@Override
	public int selectCheckId(String user_id) throws Exception {
		logger.info("selectUserCount called : " + user_id);
		return sqlSession.selectOne(namespace + ".selectCheckId", user_id);
	}

}
