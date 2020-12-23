package com.junsoo.shopping.common.dao.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.junsoo.shopping.common.vo.ProductDetailVO;
import com.junsoo.shopping.common.vo.ProductVO;
import com.junsoo.shopping.common.vo.PurchaseInfoVO;

public interface ProductDAO {
	
	public void insertProduct(ProductVO productVO) throws Exception;
	public void updateProduct(ProductVO productVO) throws Exception;
	public List<ProductVO> selectRecentlyProduct(int category) throws Exception;
	public ProductDetailVO selectProductDetail(int product_id) throws Exception;
	public List<ProductVO> selectSameStoreProduct(ProductVO productVO) throws Exception;
	public int selectCategoryProductCount(int category) throws Exception;
	public List<ProductVO> selectCategoryProducts(HashMap<String, Object> map) throws Exception;
	public List<ProductVO> selectStoreProducts(int seq_user_id) throws Exception;
	public ProductVO selectStoreProduct(ProductVO productVO) throws Exception;
	public int selectBuyProduct(Map<String, Integer> map) throws Exception;
	public List<PurchaseInfoVO> selectBuyProducts(int seq_user_id) throws Exception;
	
}
