package com.junsoo.shopping.common.dao.order;

import java.util.ArrayList;

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
	public ArrayList<OrderVO> selectAllOrder(int seq_user_id) throws Exception {
		return sqlSession.selectOne(namespace + ".selectAllOrder", seq_user_id);
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
	public void updateOrder(OrderVO orderVO) throws Exception {
		sqlSession.update(namespace + ".updateOrder", orderVO);
	}
	
	@Override
	public void deleteOrder(OrderVO orderVO) throws Exception {
		sqlSession.delete(namespace + ".deleteOrder", orderVO);
	}

	
	
	

}
