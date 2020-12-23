package com.junsoo.shopping.common.dao.reply;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.ProductReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	private final static String namespace = "com.mapper.replyMapper";
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public void insertProductReply(ProductReplyVO prVO) throws Exception {
		sqlSession.insert(namespace + ".insertProductReply", prVO);
	}

	@Override
	public List<ProductReplyVO> selectProductReplies(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".selectProductReplies", map);
	}
	
	@Override
	public int selectProductReplyCount(int product_id) throws Exception {
		return sqlSession.selectOne(namespace + ".selectProductReplyCount", product_id);
	}

	@Override
	public ProductReplyVO selectProductReply(ProductReplyVO prVO) throws Exception {
		return sqlSession.selectOne(namespace + ".selectProductReply", prVO);
	}

	@Override
	public void updateProductReplyAnswer(ProductReplyVO prVO) throws Exception {
		sqlSession.update(namespace + ".updateProductReplyAnswer", prVO);
	}

	@Override
	public void deleteProductReplyAnswer(ProductReplyVO prVO) throws Exception {
		sqlSession.delete(namespace + ".deleteProductReplyAnswer", prVO);
	}

	@Override
	public float selectProductAvgRating(int product_id) throws Exception {
		return sqlSession.selectOne(namespace + ".selectProductAvgRating", product_id);
	}

}
