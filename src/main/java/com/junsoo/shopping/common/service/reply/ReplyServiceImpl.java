package com.junsoo.shopping.common.service.reply;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.product.ProductDAO;
import com.junsoo.shopping.common.dao.reply.ReplyDAO;
import com.junsoo.shopping.common.vo.ProductReplyVO;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService{

	private static final Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);
	
	@Inject
	ReplyDAO replyDAO;
	
	@Inject
	ProductDAO productDAO;
	
	/**
	 * 댓글 작성
	 * - 댓글을 달고자하는 물품의 중복댓글 확인 후 작성
	 * @return -1: 구매하지 않음, 0: 과거에 댓글작성, 1: 성공
	 */
	@Override
	public int insertProductReply(ProductReplyVO prVO) throws Exception {
		
		Map<String, Integer> purchaseProduct = new HashMap<String, Integer>();
		purchaseProduct.put("seq_user_id", prVO.getSeq_user_id());
		purchaseProduct.put("product_id", prVO.getProduct_id());
		try {
			// 해당 제품의 구입 이력 확인
			if(productDAO.selectBuyProduct(purchaseProduct) == 0) {
				logger.warn("don't bought product. seq_user_id : " + prVO.getSeq_user_id() + 
						    " product_id : " + prVO.getProduct_id() + 
						    " user_name : " + prVO.getUser_name());
				return -1;
			}
			// 해당 제품의 현재 아이디와 중복된 댓글 확인
			if(replyDAO.selectProductReply(prVO) != null) {
				logger.warn("duplicate reply. seq_user_id : " + prVO.getSeq_user_id() + 
					    " product_id : " + prVO.getProduct_id()  +
					    " user_name : " + prVO.getUser_name());
				return 0;
			}
			replyDAO.insertProductReply(prVO);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} 
		return 1;
	}

	@Override
	public int updateProductReplyAnswer(ProductReplyVO prVO) throws Exception {
		
		try {
			replyDAO.updateProductReplyAnswer(prVO);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return 0;
		}
		return 1;
	}

}
