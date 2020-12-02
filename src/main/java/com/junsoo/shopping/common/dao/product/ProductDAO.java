package com.junsoo.shopping.common.dao.product;

import java.util.List;

import com.junsoo.shopping.common.vo.ProductVO;

public interface ProductDAO {
	
	public void insertProduct(ProductVO productVO) throws Exception;
	public List<ProductVO> selectRecentlyProduct(int category) throws Exception;
}
