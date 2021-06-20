package com.junsoo.shopping.common.service.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.reply.ReplyDAO;
import com.junsoo.shopping.common.service.product.ProductService;
import com.junsoo.shopping.common.vo.ProductReplyVO;
import com.junsoo.shopping.common.vo.paging.PaginationInfo;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService{

	private static final Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);
	
	@Inject
	private ReplyDAO replyDAO;
	
	@Inject
	private ProductService productService;
	
	@Override
	public ProductReplyVO selectProductReply(ProductReplyVO prVO) throws Exception {
		
		try {
			if(prVO.getSeq_user_id() < 1) {
				logger.error("selectProductReply() seq_user_id error " + prVO);
				return null;
			}
			if(prVO.getProduct_id() < 1) {
				logger.error("selectProductReply() product_id error " + prVO);
				return null;
			}
			return replyDAO.selectProductReply(prVO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<ProductReplyVO> selectProductReplies(int product_id, 
													 PaginationInfo paginationInfo) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("product_id", product_id);
		map.put("startIndex", paginationInfo.getStartIndex());
		map.put("pageSize", paginationInfo.getPageSize());
		try {
			return replyDAO.selectProductReplies(map);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return null;
		}
	}

	@Override
	public float selectProductAvgRating(int product_id) throws Exception {
		
		try {
			if(replyDAO.selectProductReplyCount(product_id) > 0) {
				return replyDAO.selectProductAvgRating(product_id);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return 0.0f;
	}

	@Override
	public int insertProductReply(ProductReplyVO prVO) throws Exception {
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("seq_user_id", prVO.getSeq_user_id());
		hashMap.put("product_id", prVO.getProduct_id());
		try {
			// 해당 제품의 구입 이력 확인
			if(productService.selectBuyProduct(hashMap) == null) {
				logger.warn("couldn't buy the product. seq_user_id : " + prVO.getSeq_user_id() + 
						    " product_id : " + prVO.getProduct_id() + 
						    " user_name : " + prVO.getUser_name());
				return -1;
			}
			// 해당 제품의 현재 아이디와 중복된 댓글 확인
			if(replyDAO.selectProductReply(prVO) != null) {
				logger.warn("duplicate reply. seq_user_id : " + prVO.getSeq_user_id() + 
					    " product_id : " + prVO.getProduct_id()  +
					    " user_name : " + prVO.getUser_name());
				return -1;
			}
			replyDAO.insertProductReply(prVO);
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 0;
		} 
	}

	@Override
	public int updateProductReplyAnswer(ProductReplyVO prVO) throws Exception {
		
		try {
			
			if(prVO.getSeq_user_id() < 1) {
				logger.error("updateProductReplyAnswer() seq_user_id value error. " + prVO);
				return -1;
			}
			if(prVO.getProduct_id() < 1) {
				logger.error("updateProductReplyAnswer() product_id value error. " + prVO);
				return -1;
			}
			replyDAO.updateProductReplyAnswer(prVO);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return 0;
		}
		return 1;
	}
	
	@Override
	public int deleteProductReplyAnswer(ProductReplyVO prVO) throws Exception {
		
		try {
			
			if(prVO.getSeq_user_id() < 1) {
				logger.error("deleteProductReplyAnswer() seq_user_id value error. " + prVO);
				return -1;
			}
			if(prVO.getProduct_id() < 1) {
				logger.error("deleteProductReplyAnswer() product_id value error. " + prVO);
				return -1;
			}
			replyDAO.deleteProductReplyAnswer(prVO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 0;
		}
		return 1;
	}
}
