package com.junsoo.shopping.common.service.product;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.junsoo.shopping.common.dao.product.ProductDAO;
import com.junsoo.shopping.common.vo.ProductVO;
import com.junsoo.shopping.common.vo.paging.PaginationInfo;
import com.junsoo.shopping.utils.UploadFileUtils;
import com.junsoo.shopping.utils.checker.ValueChecker;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Inject
	ProductDAO productDAO;

	@Resource(name = "uploadPath")
	private String uploadPath;
	
	ValueChecker vc = new ValueChecker();
	
	@Override
	public int insertProduct(ProductVO productVO, MultipartFile file) throws Exception {
		
		UploadFileUtils uploadFileUtils = new UploadFileUtils();
		String imgUploadPath = uploadPath + File.separator + "images";
		String ymdPath = uploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;
		
		try {
			if(file != null) {
				fileName = uploadFileUtils.fileUpload(imgUploadPath, 
						file.getOriginalFilename(), 
						file.getBytes(),
						ymdPath,
						true);
				
			} else {
				fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
			}
			productVO.setProduct_img(File.separator + "images" + ymdPath + File.separator + fileName);
			productVO.setProduct_thumbImg(File.separator + "images" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		
			
			if(productVO.getSeq_user_id() < 1 		||
				productVO.getProduct_name() == null ||
				productVO.getProduct_cnt() < 0 		||
				productVO.getProduct_desc() == null ||
				productVO.getProduct_price() < 0 	||
				productVO.getDiscount() < 0 		||
				!vc.isCheckCategory(productVO.getCategory())) {
				logger.error("Invalid args " + productVO);
				return 0;
			}
			productDAO.insertProduct(productVO);
		}
		catch (NullPointerException npe) {
			logger.error(npe.getMessage());
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 1;
	}


	@Override
	public List<ProductVO> selectRecentlyProduct(int category) throws Exception {
		
		return productDAO.selectRecentlyProduct(category);
	}


	@Override
	public int updateProduct(ProductVO productVO, MultipartFile file) throws Exception {
		
		UploadFileUtils uploadFileUtils = new UploadFileUtils();
		
		String imgUploadPath = uploadPath + File.separator + "images";
		String ymdPath = uploadFileUtils.calcPath(imgUploadPath);
		String fileName = file.getOriginalFilename();
		
		try {
			
			if(file != null && !fileName.equals("")) {
				fileName = uploadFileUtils.fileUpload(imgUploadPath, 
						file.getOriginalFilename(), 
						file.getBytes(),
						ymdPath,
						true);
				
				productVO.setProduct_img(File.separator + "images" + ymdPath + File.separator + fileName);
				productVO.setProduct_thumbImg(File.separator + "images" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
			}		
			
			if(productVO.getSeq_user_id() < 1 		||
				productVO.getProduct_name() == null ||
				productVO.getProduct_cnt() < 0 		||
				productVO.getProduct_desc() == null ||
				productVO.getProduct_price() < 0 	||
				productVO.getDiscount() < 0 		||
				!vc.isCheckCategory(productVO.getCategory())) {
				logger.error("Invalid args " + productVO);
				return 0;
			}
			productDAO.updateProduct(productVO);
		}
		catch (NullPointerException npe) {
			logger.error(npe.getMessage());
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 1;
	}


	@Override
	public List<ProductVO> selectCategoryProducts(ProductVO productVO, PaginationInfo paginationInfo) throws Exception {
		
		List<ProductVO> productList = Collections.emptyList();
		
		int productTotalCount = productDAO.selectCategoryProductCount(productVO.getCategory());
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("category", productVO.getCategory());
		map.put("startIndex", paginationInfo.getStartIndex());
		map.put("pageSize", paginationInfo.getPageSize());
		if(productTotalCount > 0) {
			productList = productDAO.selectCategoryProducts(map);
		}
		
		return productList;
	}



}
