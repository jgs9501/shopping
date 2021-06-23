package com.junsoo.shopping.common.service.reply;

import java.util.List;

import com.junsoo.shopping.common.vo.ProductReplyVO;
import com.junsoo.shopping.common.vo.paging.PaginationInfo;

/**
 * 상품 댓글관련 메소드
 * @author jjsoo
 *
 */
public interface ReplyService {

	/**
	 * 회원의 특정 상품 구입내역 조회
	 * @param prVO
	 * @return
	 * @throws Exception
	 */
	public ProductReplyVO selectProductReply(ProductReplyVO prVO) throws Exception;
	
	/**
	 * 특정 상품의 댓글 조회 (페이징)
	 * @param product_id
	 * @param paginationInfo
	 * @return
	 * @throws Exception
	 */
	public List<ProductReplyVO> selectProductReplies(int product_id, PaginationInfo paginationInfo) throws Exception;
	
	/**
	 * 특정 상품의 평가 평균
	 * @param product_id
	 * @return
	 * @throws Exception
	 */
	public float selectProductAvgRating(int product_id) throws Exception;
	
	/**
	 * 해당 상품에 달린 댓글 개수 조회
	 * @param product_id
	 * @return
	 * @throws Exception
	 */
	public int selectProductReplyCount(int product_id) throws Exception;
	
	/**
	 * 댓글 작성<br>
	 * - 댓글을 달고자하는 물품의 중복댓글 확인 후 작성<br>
	 * -1: 구매하지 않음, 0: 과거에 댓글작성, 1: 성공
	 * @param prVO
	 * @return
	 * @throws Exception
	 */
	public int insertProductReply(ProductReplyVO prVO) throws Exception;
	
	/**
	 * 댓글의 답변 수정
	 * @param prVO
	 * @return
	 * @throws Exception
	 */
	public int updateProductReplyAnswer(ProductReplyVO prVO) throws Exception;
	
	/**
	 * 댓글의 답변 삭제
	 * @param prVO
	 * @return
	 * @throws Exception
	 */
	public int deleteProductReplyAnswer(ProductReplyVO prVO) throws Exception;
}
