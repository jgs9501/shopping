package com.junsoo.shopping.common.service.cart;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.cart.CartDAO;
import com.junsoo.shopping.common.vo.CartVO;

@Service
@Transactional
public class CartServiceImpl implements CartService{

	private static final Logger logger = LoggerFactory.getLogger(CartService.class);
	
	@Inject
	CartDAO cartDAO;
	
	/**
	 * 장바구니 cartVO의 데이터 확인
	 * 0 : 데이터가 null일 경우
	 * 1 : 데이터가 정상일 경우
	 */
	@Override
	public int insertCart(CartVO cartVO) throws Exception {
		try {
			logger.info(cartVO.toString());
			if(cartVO.equals(null)) {
				return 0;
			}
			cartDAO.insertCart(cartVO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 0;
		}
		return 1;
	}

	/**
	 * 특정 고객의 장바구니에 담긴 모든 물품 출력
	 */
	@Override
	public List<HashMap<String, Object>> selectAllCart(int seq_user_id) throws Exception {
		try {
			if(seq_user_id > 0) {
				return cartDAO.selectAllCart(seq_user_id);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 장바구니 물품 삭제 
	 */
	@Override
	public int deleteCart(CartVO cartVO) throws Exception {
		try {
			int seq_user_id = cartVO.getSeq_user_id();
			int cart_id = cartVO.getCart_id();
			
			if(seq_user_id <= 0 || cart_id <= 0) {
				logger.warn("invalid seq_user_id=" + seq_user_id + ", cart_id=" + cart_id);
				return 0;
			}
			cartDAO.deleteCart(cartVO);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 1;
	}

}
