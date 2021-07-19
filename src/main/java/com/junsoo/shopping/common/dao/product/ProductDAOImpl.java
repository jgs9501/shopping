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
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public ProductDetailVO selectProductDetail(int product_id) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectProductDetail", product_id);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<ProductVO> selectSameStoreProduct(ProductVO productVO) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectSameStoreProduct", productVO);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int selectCategoryProductCount(HashMap<String, Object> hashMap) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectCategoryProductCount", hashMap);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<ProductVO> selectCategoryProducts(HashMap<String, Object> hashMap) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectCategoryProducts", hashMap);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public List<ProductVO> selectStoreProducts(int seq_user_id) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectStoreProducts", seq_user_id);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public ProductVO selectStoreProduct(ProductVO productVO) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectStoreProduct", productVO);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public HashMap<String, Object> selectBuyProduct(Map<String, Object> hashMap) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectBuyProduct", hashMap);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public List<HashMap<String, Object>> selectBuyProducts(int seq_user_id) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectBuyProducts", seq_user_id);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<HashMap<String, Object>> selectFavoriteProduct() throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectFavoriteProduct");
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public int updateProductAmount(HashMap<String, Object> hashMap) throws Exception {
		
		try {
			return sqlSession.update(namespace + ".updateProductAmount", hashMap);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void insertProduct(ProductVO productVO) throws Exception {
		
		try {
			
			sqlSession.insert(namespace + ".insertProduct", productVO);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateProduct(ProductVO productVO) throws Exception {
		
		try {
			
			sqlSession.update(namespace + ".updateProduct", productVO);
		} catch (DataAccessException dae) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			throw dae;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}


}
