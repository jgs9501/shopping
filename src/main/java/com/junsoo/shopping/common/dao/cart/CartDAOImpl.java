package com.junsoo.shopping.common.dao.cart;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.CartVO;

@Repository
public class CartDAOImpl implements CartDAO {

	private static final Logger logger = LoggerFactory.getLogger(CartDAO.class);
	private static final String namespace = "com.mapper.cartMapper";
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public void insertCart(CartVO cartVO) throws Exception {
		
		try {
			
			sqlSession.insert(namespace + ".insertCart", cartVO);
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
	public List<HashMap<String, Object>> selectAllCart(int seq_user_id) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectAllCart", seq_user_id);
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
	public void deleteCart(CartVO cartVO) throws Exception {
		
		try {
			
			sqlSession.delete(namespace + ".deleteCart", cartVO);
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
	public void deleteAllCart(int seq_user_id) throws Exception {
		
		try {
			
			sqlSession.delete(namespace + ".deleteAllCart", seq_user_id);
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
