package com.junsoo.shopping.common.dao.order;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.OrderDetailVO;

@Repository
public class OrderDetailDAOImpl implements OrderDetailDAO {

	private static final Logger logger = LoggerFactory.getLogger(OrderDetailDAO.class);
	private static final String namespace = "com.mapper.orderDetailMapper";
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<OrderDetailVO> selectOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		return sqlSession.selectList(namespace + ".selectOrderDetail", orderDetailVO);
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

}
