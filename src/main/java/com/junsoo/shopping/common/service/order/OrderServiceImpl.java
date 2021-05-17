package com.junsoo.shopping.common.service.order;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.order.OrderDAO;
import com.junsoo.shopping.common.vo.OrderVO;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Inject
	OrderDAO orderDAO;

	@Override
	public OrderVO selectOrder(OrderVO orderVO) throws Exception {
		try {
			if(orderVO.getSeq_user_id() < 1) {
				logger.error("selectOrder method error : seq_user_id does not exist.");
				return null;
			}
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return orderDAO.selectOrder(orderVO);
	}

	@Override
	public int insertOrder(OrderVO orderVO) throws Exception {
		
		try {
			if(orderVO.getSeq_user_id() < 1) {
				logger.error("insertOrder method error : seq_user_id does not exist.");
				return 300;
			}
			if(orderVO.getOrder_id() == "") {
				logger.error("insertOrder method error : order_id does not exist.");
				return 301;
			}
			orderDAO.insertOrder(orderVO);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -2;
		}
		return 1;
	}

	@Override
	public int updateOrder(OrderVO orderVO) throws Exception {
		try {
			if(orderVO.getSeq_user_id() < 1) {
				logger.error("updateOrder method error : seq_user_id does not exist.");
				return 300;
			}
			if(orderVO.getOrder_id() == "") {
				logger.error("updateOrder method error : order_id does not exist.");
				return 301;
			}
			// N : 주문완료, C : 주문취소, P : 배송중, E : 배송완료
			if(orderVO.getOrder_status() != 'N' &&
			   orderVO.getOrder_status() != 'P' &&
			   orderVO.getOrder_status() != 'E' &&
			   orderVO.getOrder_status() != 'C') {
				logger.error("updateOrder method error : check order_status value");
				return 302;
			}
			orderDAO.updateOrder(orderVO);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -2;
		}
		return 1;
	}

	@Override
	public int deleteOrder(OrderVO orderVO) throws Exception {
		try {
			if(orderVO.getSeq_user_id() < 1) {
				logger.error("deleteOrder method error : seq_user_id does not exist.");
				return 300;
			}
			if(orderVO.getOrder_id() == "") {
				logger.error("deleteOrder method error : order_id does not exist.");
				return 301;
			}
			orderDAO.deleteOrder(orderVO);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -2;
		}
		return 1;
	}


	

}
