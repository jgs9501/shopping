package com.junsoo.shopping.common.service.reply;

import com.junsoo.shopping.common.vo.ProductReplyVO;

public interface ReplyService {

	public int insertProductReply(ProductReplyVO prVO) throws Exception;
	public int updateProductReplyAnswer(ProductReplyVO prVO) throws Exception;
}
