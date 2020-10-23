package com.junsoo.shopping.common.dao.register;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.UserVO;

@Repository
public class RegisterDAOImpl implements RegisterDAO{

	@Inject
	private SqlSession sqlSession;
	private static final Logger logger = LoggerFactory.getLogger(RegisterDAO.class);
	private static final String namespace = "com.mapper.userDataMapper";
	
	@Override
	public void insertUser(UserVO vo) throws Exception {
		logger.info("insertUser called");
		sqlSession.insert(namespace + ".insertUser", vo);
	}

	@Override
	public int selectCheckId(String user_id) throws Exception {
		logger.info("selectCheckId called");
		return sqlSession.selectOne(namespace + ".selectCheckId", user_id);
	}

}
