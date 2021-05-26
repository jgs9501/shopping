package com.junsoo.shopping.common.dao.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.OrderVO;

@Repository
public class OrderDAOImpl implements OrderDAO{

	private static final Logger logger = LoggerFactory.getLogger(OrderDAO.class);
	private static final String namespace = "com.mapper.orderMapper";
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<OrderVO> selectAllOrder(int seq_user_id) throws Exception {
		return sqlSession.selectList(namespace + ".selectAllOrder", seq_user_id);
	}
	
	@Override
	public OrderVO selectOrder(OrderVO orderVO) throws Exception {
		return sqlSession.selectOne(namespace + ".selectOrder", orderVO);
	}
	
	@Override
	public void insertOrder(OrderVO orderVO) throws Exception {
		sqlSession.insert(namespace + ".insertOrder", orderVO); 
	}
	
	@Override
	public void updateOrderStatus(OrderVO orderVO) throws Exception {
		sqlSession.update(namespace + ".updateOrderStatus", orderVO);
	}
	
	@Override
	public void updateOrderTotalPrice(HashMap<String, Object> map) throws Exception {
		sqlSession.update(namespace + ".updateOrderTotalPrice", map);
	}
	
	@Override
	public void deleteOrder(OrderVO orderVO) throws Exception {
		sqlSession.delete(namespace + ".deleteOrder", orderVO);
	}

	@Override
	public void deleteOrder(HashMap<String, Object> map) throws Exception {
		sqlSession.delete(namespace + ".deleteOrder", map);		
	}

	

	
	
	

}
