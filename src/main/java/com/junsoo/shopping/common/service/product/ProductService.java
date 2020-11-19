package com.junsoo.shopping.common.service.product;

import org.springframework.web.multipart.MultipartFile;

import com.junsoo.shopping.common.vo.ProductVO;

public interface ProductService {

	public int insertProduct(ProductVO productVO, MultipartFile file) throws Exception;
}
