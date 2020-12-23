package com.junsoo.shopping.common.service.reply;

import java.util.List;

import com.junsoo.shopping.common.vo.ProductReplyVO;
import com.junsoo.shopping.common.vo.paging.PaginationInfo;

public interface ReplyService {

	public int insertProductReply(ProductReplyVO prVO) throws Exception;
	public int updateProductReplyAnswer(ProductReplyVO prVO) throws Exception;
	public List<ProductReplyVO> selectProductReplies(int product_id, PaginationInfo paginationInfo) throws Exception;
	public float selectProductAvgRating(int product_id) throws Exception;
}
