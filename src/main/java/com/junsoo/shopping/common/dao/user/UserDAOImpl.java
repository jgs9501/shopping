package com.junsoo.shopping.common.dao.user;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.StoreVO;
import com.junsoo.shopping.common.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	private static final String namespace = "com.mapper.userDataMapper";

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public String selectPassword(String userId) throws Exception {
		try {
			
			return sqlSession.selectOne(namespace + ".selectPassword", userId);
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
	public UserVO selectOneUser(UserVO userVO) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectOneUser", userVO);
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
	public UserVO selectOneUser(String userId) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectOneUser", userId);
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
	public UserVO selectOneUser(int seq_user_id) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectSeqUser", seq_user_id);
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
	public void updateUser(UserVO userVO) throws Exception {
		
		try {
			
			sqlSession.update(namespace + ".updateUser", userVO);
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
	public void updatePassword(UserVO userVO) throws Exception {
		
		try {
			
			sqlSession.update(namespace + ".updatePassword", userVO);
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
	public void insertUser(UserVO userVO) throws Exception {
		
		try {
			
			sqlSession.insert(namespace + ".insertUser", userVO);
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
	public int selectCheckId(String userId) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectCheckId", userId);
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
	public void signOut(UserVO userVO) throws Exception {
		
		try {
			
			sqlSession.update(namespace + ".signOutUser", userVO);
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
	public void insertUserToStoreAuth(StoreVO storeVO) throws Exception {
		
		try {
			
			sqlSession.insert(namespace + ".insertUserToStore", storeVO);
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
