package com.junsoo.shopping.common.dao.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.ProductDetailVO;
import com.junsoo.shopping.common.vo.ProductVO;
import com.junsoo.shopping.common.vo.PurchaseInfoVO;

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
	public List<ProductVO> selectCategoryProducts(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".selectCategoryProducts", map);
	}

	@Override
	public int selectCategoryProductCount(int category) throws Exception {
		return sqlSession.selectOne(namespace + ".selectCategoryProductCount", category);
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
	
	@Override
	public int selectBuyProduct(Map<String, Integer> map) throws Exception {
		return sqlSession.selectOne(namespace + ".selectBuyProduct", map);
	}

	@Override
	public List<PurchaseInfoVO> selectBuyProducts(int seq_user_id) throws Exception {
		return sqlSession.selectList(namespace + ".selectBuyProducts", seq_user_id);
	}


}
