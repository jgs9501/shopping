package com.junsoo.shopping.common.dao.cart;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		sqlSession.insert(namespace + ".insertCart", cartVO);
	}

	@Override
	public List<HashMap<String, Object>> selectAllCart(int seq_user_id) throws Exception {
		return sqlSession.selectList(namespace + ".selectAllCart", seq_user_id);
	}

	@Override
	public void deleteCart(CartVO cartVO) throws Exception {
		sqlSession.delete(namespace + ".deleteCart", cartVO);
	}

	@Override
	public void deleteAllCart(int seq_user_id) throws Exception {
		sqlSession.delete(namespace + ".deleteAllCart", seq_user_id);
	}
	
	
	
}
