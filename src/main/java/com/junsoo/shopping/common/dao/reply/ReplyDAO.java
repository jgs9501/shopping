package com.junsoo.shopping.common.dao.reply;

import java.util.List;

import com.junsoo.shopping.common.vo.ProductReplyVO;

public interface ReplyDAO {

	public List<ProductReplyVO> selectProductReplies(int product_id) throws Exception;
	public ProductReplyVO selectProductReply(ProductReplyVO prVO) throws Exception;
	public void insertProductReply(ProductReplyVO prVO) throws Exception;
	public void updateProductReplyAnswer(ProductReplyVO prVO) throws Exception;
	public void deleteProductReplyAnswer(ProductReplyVO prVO) throws Exception;
}
