package com.junsoo.shopping.common.dao.order;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.OrderDetailVO;
import com.junsoo.shopping.common.vo.OrderVO;

@Repository
public class OrderDetailDAOImpl implements OrderDetailDAO {

	private static final Logger logger = LoggerFactory.getLogger(OrderDetailDAO.class);
	private static final String namespace = "com.mapper.orderDetailMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<OrderDetailVO> selectAllOrderDetail(int seq_user_id) throws Exception {
		return sqlSession.selectList(namespace + ".selectAllOrderDetail", seq_user_id);
	}
	
	@Override
	public List<OrderDetailVO> selectOrderDetail(OrderVO orderVO) throws Exception {
		return sqlSession.selectList(namespace + ".selectOrderDetail", orderVO);
	}
	
	@Override
	public List<OrderDetailVO> selectOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		return sqlSession.selectList(namespace + ".selectOrderDetail", orderDetailVO);
	}

	@Override
	public int selectCntOrderDetail(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespace + ".selectCntOrderDetail", map);
	}
	
	@Override
	public void insertOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		sqlSession.insert(namespace + ".insertOrderDetail", orderDetailVO); 
	}

	@Override
	public void updateOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		sqlSession.update(namespace + ".updateOrderDetail", orderDetailVO);
	}

	@Override
	public void deleteOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		sqlSession.delete(namespace + ".deleteOrderDetail", orderDetailVO);
	}

	@Override
	public void deleteOrderDetail(HashMap<String, Object> map) throws Exception {
		sqlSession.delete(namespace + ".deleteOrderDetail", map);
	}


}
