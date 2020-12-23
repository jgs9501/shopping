package com.junsoo.shopping.common.service.product;



import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.junsoo.shopping.common.vo.ProductVO;
import com.junsoo.shopping.common.vo.paging.PaginationInfo;

public interface ProductService {

	public int insertProduct(ProductVO productVO, MultipartFile file) throws Exception;
	public List<ProductVO> selectRecentlyProduct(int category) throws Exception;
	public int updateProduct(ProductVO productVO, MultipartFile file) throws Exception;
	public List<ProductVO> selectCategoryProducts(ProductVO productVO, PaginationInfo paginationInfo) throws Exception;
}
