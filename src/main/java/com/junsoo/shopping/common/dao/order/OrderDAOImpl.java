package com.junsoo.shopping.common.dao.order;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.OrderVO;

@Repository
public class OrderDAOImpl implements OrderDAO{

	private static final Logger logger = LoggerFactory.getLogger(OrderDAO.class);
	private static final String namespace = "com.mapper.orderMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<OrderVO> selectAllOrder(int seq_user_id) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectAllOrder", seq_user_id);
		} catch (DataAccessException dae) {
			logger.error("selectAllOrder() Data access Exception. " + dae.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public OrderVO selectOrder(OrderVO orderVO) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectOrder", orderVO);
		} catch (DataAccessException dae) {
			logger.error("selectOrder() Data access Exception. " + dae.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public void insertOrder(OrderVO orderVO) throws Exception {
		
		try {
			
			sqlSession.insert(namespace + ".insertOrder", orderVO); 
		} catch (DataAccessException dae) {
			logger.error("insertOrder() Data access Exception. " + dae.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	public void updateOrderStatus(OrderVO orderVO) throws Exception {
		
		try {
			
			sqlSession.update(namespace + ".updateOrderStatus", orderVO);
		} catch (DataAccessException dae) {
			logger.error("updateOrderStatus() Data access Exception. " + dae.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	public void updateOrderTotalPrice(HashMap<String, Object> map) throws Exception {
		
		try {
			
			sqlSession.update(namespace + ".updateOrderTotalPrice", map);
		} catch (DataAccessException dae) {
			logger.error("updateOrderTotalPrice() Data access Exception. " + dae.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	public void deleteOrder(OrderVO orderVO) throws Exception {
		
		try {
			
			sqlSession.delete(namespace + ".deleteOrder", orderVO);
		} catch (DataAccessException dae) {
			logger.error("deleteOrder() Data access Exception. " + dae.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void deleteOrder(HashMap<String, Object> map) throws Exception {
		
		try {
			
			sqlSession.delete(namespace + ".deleteOrder", map);		
		} catch (DataAccessException dae) {
			logger.error("deleteOrder() Data access Exception. " + dae.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
