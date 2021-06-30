package com.junsoo.shopping.common.dao.reply;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.ProductReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	private final static Logger logger = LoggerFactory.getLogger(ReplyDAO.class);
	private final static String namespace = "com.mapper.replyMapper";
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public void insertProductReply(ProductReplyVO prVO) throws Exception {
		
		try {
			
			sqlSession.insert(namespace + ".insertProductReply", prVO);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			logger.error(dae.getMessage());
			throw dae;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<ProductReplyVO> selectProductReplies(HashMap<String, Object> map) throws Exception {

		try {
			
			return sqlSession.selectList(namespace + ".selectProductReplies", map);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			logger.error(dae.getMessage());
			throw dae;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		} 
	}
	
	@Override
	public int selectProductReplyCount(int product_id) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectProductReplyCount", product_id);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			logger.error(dae.getMessage());
			throw dae;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		} 
	}

	@Override
	public ProductReplyVO selectProductReply(ProductReplyVO prVO) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectProductReply", prVO);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			logger.error(dae.getMessage());
			throw dae;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void updateProductReplyAnswer(ProductReplyVO prVO) throws Exception {
		
		try {
			
			sqlSession.update(namespace + ".updateProductReplyAnswer", prVO);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			logger.error(dae.getMessage());
			throw dae;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void deleteProductReplyAnswer(ProductReplyVO prVO) throws Exception {
		
		try {
			
			sqlSession.delete(namespace + ".deleteProductReplyAnswer", prVO);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			logger.error(dae.getMessage());
			throw dae;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public float selectProductAvgRating(int product_id) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectProductAvgRating", product_id);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			logger.error(dae.getMessage());
			throw dae;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
	}

}
