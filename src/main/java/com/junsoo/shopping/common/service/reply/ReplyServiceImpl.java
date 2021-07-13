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
			ProductReplyVO resultVO = new ProductReplyVO();
			if(prVO.getSeq_user_id() < 1) {
				logger.error("selectProductReply() seq_user_id error " + prVO);
				return null;
			}
			if(prVO.getProduct_id() < 1) {
				logger.error("selectProductReply() product_id error " + prVO);
				return null;
			}
			resultVO = replyDAO.selectProductReply(prVO);
			resultVO.setContent(resultVO.getContent().replaceAll("<br>", "\n"));
			return resultVO;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage());
			nfe.printStackTrace();
			throw nfe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
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
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage());
			nfe.printStackTrace();
			throw nfe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public float selectProductAvgRating(int product_id) throws Exception {
		
		try {
			if(replyDAO.selectProductReplyCount(product_id) > 0) {
				return replyDAO.selectProductAvgRating(product_id);
			}
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage());
			nfe.printStackTrace();
			throw nfe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return 0.0f;
	}

	@Override
	public int selectProductReplyCount(int product_id) throws Exception {
		
		try {
			if(product_id < 1) {
				logger.error("selectProductReplyCount() product_id error. " + product_id);
				product_id = 0;
			}
			return replyDAO.selectProductReplyCount(product_id);
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage());
			nfe.printStackTrace();
			throw nfe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public int insertProductReply(ProductReplyVO prVO) throws Exception {
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("seq_user_id", prVO.getSeq_user_id());
		hashMap.put("product_id", prVO.getProduct_id());
		try {
			// 구입내역
			Map<String, Object> order_historyMap = new HashMap<String, Object>();
			order_historyMap = productService.selectBuyProduct(hashMap);
			System.out.println(prVO);
			System.out.println(order_historyMap);
			// 해당 제품의 구매 이력 확인
			// 구매이력, 리뷰 작성 여부 확인, 평점 입력 확인
			if(order_historyMap == null || order_historyMap.get("rating") != null) {
				logger.info("couldn't write the product reply. seq_user_id : " + prVO.getSeq_user_id() + 
						    " product_id : " + prVO.getProduct_id() + 
						    " user_name : " + prVO.getUser_name());
				return -1;
			}
			prVO.setContent(prVO.getContent().replaceAll("\n", "<br>"));
			prVO.setAnswer(prVO.getAnswer().replaceAll("\n", "<br>"));
			replyDAO.insertProductReply(prVO);
			return 1;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage());
			nfe.printStackTrace();
			throw nfe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
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
			prVO.setAnswer(prVO.getAnswer().replaceAll("\n", "<br>"));
			replyDAO.updateProductReplyAnswer(prVO);
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage());
			nfe.printStackTrace();
			throw nfe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
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
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage());
			nfe.printStackTrace();
			throw nfe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
}
