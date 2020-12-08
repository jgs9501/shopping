package com.junsoo.shopping.common.dao.product;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.ProductDetailVO;
import com.junsoo.shopping.common.vo.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProductDAO.class);
	private static final String namespace = "com.mapper.productMapper";
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public void insertProduct(ProductVO productVO) throws Exception {
		sqlSession.insert(namespace + ".insertProduct", productVO);
	}

	@Override
	public List<ProductVO> selectRecentlyProduct(int category) throws Exception {
		return sqlSession.selectList(namespace + ".selectRecentlyProduct", category);
	}

	@Override
	public ProductDetailVO selectProductDetail(int product_id) throws Exception {
		return sqlSession.selectOne(namespace + ".selectProductDetail", product_id);
	}

	@Override
	public List<ProductVO> selectSameStoreProduct(ProductVO productVO) throws Exception {
		return sqlSession.selectList(namespace + ".selectSameStoreProduct", productVO);
	}

	@Override
	public List<ProductVO> selectAllProduct(int category) throws Exception {
		return sqlSession.selectList(namespace + ".selectAllProduct", category);
	}

	@Override
	public List<ProductVO> selectStoreProducts(int seq_user_id) throws Exception {
		return sqlSession.selectList(namespace + ".selectStoreProducts", seq_user_id);
	}
	
	@Override
	public ProductVO selectStoreProduct(ProductVO productVO) throws Exception {
		return sqlSession.selectOne(namespace + ".selectStoreProduct", productVO);
	}

	@Override
	public void updateProduct(ProductVO productVO) throws Exception {
		sqlSession.update(namespace + ".updateProduct", productVO);
	}

}
