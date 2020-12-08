package com.junsoo.shopping.common.dao.product;

import java.util.List;

import com.junsoo.shopping.common.vo.ProductDetailVO;
import com.junsoo.shopping.common.vo.ProductVO;

public interface ProductDAO {
	
	public void insertProduct(ProductVO productVO) throws Exception;
	public void updateProduct(ProductVO productVO) throws Exception;
	public List<ProductVO> selectRecentlyProduct(int category) throws Exception;
	public ProductDetailVO selectProductDetail(int product_id) throws Exception;
	public List<ProductVO> selectSameStoreProduct(ProductVO productVO) throws Exception;
	public List<ProductVO> selectAllProduct(int category) throws Exception;
	public List<ProductVO> selectStoreProducts(int seq_user_id) throws Exception;
	public ProductVO selectStoreProduct(ProductVO productVO) throws Exception;
}
