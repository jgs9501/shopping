package com.junsoo.shopping.common.dao.order;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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
		
		try {
			
			return sqlSession.selectList(namespace + ".selectAllOrderDetail", seq_user_id);
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
	public List<OrderDetailVO> selectOrderDetail(OrderVO orderVO) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectOrderDetail", orderVO);
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
	public List<OrderDetailVO> selectOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectOrderDetail", orderDetailVO);
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
	public int selectCntOrderDetail(HashMap<String, Object> map) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectCntOrderDetail", map);
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
	public void insertOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		
		try {
			
			sqlSession.insert(namespace + ".insertOrderDetail", orderDetailVO); 
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
	public void updateOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		
		try {
			
			sqlSession.update(namespace + ".updateOrderDetail", orderDetailVO);
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
	public void deleteOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		
		try {
			
			sqlSession.delete(namespace + ".deleteOrderDetail", orderDetailVO);
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
	public void deleteOrderDetail(HashMap<String, Object> map) throws Exception {
		
		try {
			
			sqlSession.delete(namespace + ".deleteOrderDetail", map);
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
