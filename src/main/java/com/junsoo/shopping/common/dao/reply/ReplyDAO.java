package com.junsoo.shopping.common.dao.reply;

import java.util.HashMap;
import java.util.List;

import com.junsoo.shopping.common.vo.ProductReplyVO;

/**
 * 댓글관련 replyMapper.xml에 정의된 SQL실행
 * @author jjsoo
 *
 */
public interface ReplyDAO {

	/**
	 * 특정 상품에 작성된 댓글 조회 메소드
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<ProductReplyVO> selectProductReplies(HashMap<String, Object> map) throws Exception;
	
	/**
	 * 특정 상품에 작성된 댓글 개수 조회 메소드
	 * @param product_id
	 * @return
	 * @throws Exception
	 */
	public int selectProductReplyCount(int product_id) throws Exception;
	
	/**
	 * 특정 상품에서의 특정 댓글 조회 메소드
	 * @param prVO
	 * @return
	 * @throws Exception
	 */
	public ProductReplyVO selectProductReply(ProductReplyVO prVO) throws Exception;
	
	/**
	 * 특정 상품 댓글 작성 메소드
	 * @param prVO
	 * @throws Exception
	 */
	public void insertProductReply(ProductReplyVO prVO) throws Exception;
	
	/**
	 * 특정 상품 댓글에 출판 점포가 작성하는 대댓글 추가 메소드
	 * @param prVO
	 * @throws Exception
	 */
	public void updateProductReplyAnswer(ProductReplyVO prVO) throws Exception;
	
	/**
	 * 특정 상품 댓글에 출판 점포가 대댓글 삭제하는 메소드
	 * @param prVO
	 * @throws Exception
	 */
	public void deleteProductReplyAnswer(ProductReplyVO prVO) throws Exception;
	
	/**
	 * 특정 상품의 댓글 평점을 조회하는 메소드
	 * @param product_id
	 * @return
	 * @throws Exception
	 */
	public float selectProductAvgRating(int product_id) throws Exception;
}
