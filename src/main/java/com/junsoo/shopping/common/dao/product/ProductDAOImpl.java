package com.junsoo.shopping.common.dao.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.ProductDetailVO;
import com.junsoo.shopping.common.vo.ProductVO;
import com.junsoo.shopping.common.vo.PurchaseInfoVO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProductDAO.class);
	private static final String namespace = "com.mapper.productMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<ProductVO> selectRecentlyProduct(int category) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectRecentlyProduct", category);
		} catch (DataAccessException dae) {
			logger.error("selectRecentlyProduct() Data access Exception. " + dae.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public ProductDetailVO selectProductDetail(int product_id) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectProductDetail", product_id);
		} catch (DataAccessException dae) {
			logger.error("selectProductDetail() Data access Exception. " + dae.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<ProductVO> selectSameStoreProduct(ProductVO productVO) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectSameStoreProduct", productVO);
		} catch (DataAccessException dae) {
			logger.error("selectSameStoreProduct() Data access Exception. " + dae.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public int selectCategoryProductCount(HashMap<String, Object> hashMap) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectCategoryProductCount", hashMap);
		} catch (DataAccessException dae) {
			logger.error("selectCategoryProductCount() Data access Exception. " + dae.getMessage());
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -1;
		}
	}

	@Override
	public List<ProductVO> selectCategoryProducts(HashMap<String, Object> hashMap) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectCategoryProducts", hashMap);
		} catch (DataAccessException dae) {
			logger.error("selectCategoryProducts() Data access Exception. " + dae.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<ProductVO> selectStoreProducts(int seq_user_id) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectStoreProducts", seq_user_id);
		} catch (DataAccessException dae) {
			logger.error("selectStoreProducts() Data access Exception. " + dae.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public ProductVO selectStoreProduct(ProductVO productVO) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectStoreProduct", productVO);
		} catch (DataAccessException dae) {
			logger.error("selectStoreProduct() Data access Exception. " + dae.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public HashMap<String, Object> selectBuyProduct(Map<String, Object> hashMap) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectBuyProduct", hashMap);
		} catch (DataAccessException dae) {
			logger.error("selectBuyProduct() Data access Exception. " + dae.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<HashMap<String, Object>> selectBuyProducts(int seq_user_id) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectBuyProducts", seq_user_id);
		} catch (DataAccessException dae) {
			logger.error("selectBuyProducts() Data access Exception. " + dae.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void insertProduct(ProductVO productVO) throws Exception {
		
		try {
			
			sqlSession.insert(namespace + ".insertProduct", productVO);
		} catch (DataAccessException dae) {
			logger.error("insertProduct() Data access Exception. " + dae.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void updateProduct(ProductVO productVO) throws Exception {
		
		try {
			
			sqlSession.update(namespace + ".updateProduct", productVO);
		} catch (DataAccessException dae) {
			logger.error("updateProduct() Data access Exception. " + dae.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}


}
