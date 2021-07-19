package com.junsoo.shopping.common.dao.user;

import java.util.HashMap;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.UserVO;

@Repository
public class UserpointDAOImpl implements UserpointDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserpointDAO.class);
	private static final String namespace = "com.mapper.userPointMapper";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public int selectCountUserId(String user_id) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectCountUserId", user_id);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public int selectUserPoint(String user_id) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectUserPoint", user_id);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void insertUserPoint(UserVO userVO) throws Exception {
		
		try {
			
			sqlSession.insert(namespace + ".insertUserPoint", userVO);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int updateUserPoint(HashMap<String, Object> userPointMap) throws Exception {
		
		try {
			
			return sqlSession.update(namespace + ".updateUserPoint", userPointMap);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int deleteUserPoint(String user_id) throws Exception {
		
		try {
			
			return sqlSession.delete(namespace + ".deleteUserPoint", user_id);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

}
