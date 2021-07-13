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
	private CartDAO cartDAO;
	
	@Override
	public int insertCart(CartVO cartVO) throws Exception {
		
		try {
			if(cartVO.equals(null)) {
				return 0;
			}
			cartDAO.insertCart(cartVO);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return 1;
	}

	@Override
	public List<HashMap<String, Object>> selectAllCart(int seq_user_id) throws Exception {
		
		try {
			if(seq_user_id > 0) {
				return cartDAO.selectAllCart(seq_user_id);
			}
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return null;
	}

	@Override
	public int updateCartAmount(CartVO cartVO) throws Exception {
		int code = 0;
		try {
			if(cartVO.getCart_id() < 1) {
				throw new NullPointerException();
			}
			cartDAO.updateCartAmount(cartVO);
			code = 200;
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return code;
	}
	
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
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
}
