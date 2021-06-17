package com.junsoo.shopping.common.service.product;

import java.io.File;
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
	
	@Override
	public List<ProductVO> selectRecentlyProduct(int category) throws Exception {
		
		ValueChecker vc = new ValueChecker();
		try {
			
			if(!vc.isCheckCategory(category)) {
				logger.error("selectRecentlyProduct() category does not exist. error category value : " + category);
				return null;
			}
			return productDAO.selectRecentlyProduct(category);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public int selectCategoryProductCount(HashMap<String, Object> hashMap) throws Exception {
		
		ValueChecker vc = new ValueChecker();
		int category = 0;
		
		try {
			
			// HashMap 키 값 체크
			if(!hashMap.containsKey("category")) {
				logger.error("selectSearchCategoryCount() 'category' does not exist in HashMap");
				return -1;
			}
			if(!hashMap.containsKey("search")) {
				logger.error("selectSearchCategoryCount() 'search' does not exist in HashMap");
				return -1;
			}
			category = (int)hashMap.get("category");
			// 존재하는 카테고리인지 확인
			if(!vc.isCheckCategory(category)) {
				logger.error("selectSearchCategoryCount() category does not exist. error category value : " 
					+ category);
				return -1;
			}
			return productDAO.selectCategoryProductCount(hashMap);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -1;
		}
	}
	
	@Override
	public List<ProductVO> selectCategoryProducts(HashMap<String, Object> hashMap) throws Exception {
		
		ValueChecker vc = new ValueChecker();
		int category = 0;
		
		try {
			
			// HashMap 키 값 체크
			if(!hashMap.containsKey("category")) {
				logger.error("selectSearchCategoryCount() 'category' does not exist in HashMap");
				return null;
			}
			if(!hashMap.containsKey("search")) {
				logger.error("selectSearchCategoryCount() 'search' does not exist in HashMap");
				return null;
			}
			category = (int)hashMap.get("category");
			// 존재하는 카테고리인지 확인
			if(!vc.isCheckCategory(category)) {
				logger.error("selectSearchCategoryCount() category does not exist. error category value : " 
						+ category);
				return null;
			}
			
			return productDAO.selectCategoryProducts(hashMap);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public int insertProduct(ProductVO productVO, MultipartFile file) throws Exception {
		
		UploadFileUtils uploadFileUtils = new UploadFileUtils();
		ValueChecker vc = new ValueChecker();
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
	public int updateProduct(ProductVO productVO, MultipartFile file) throws Exception {
		
		UploadFileUtils uploadFileUtils = new UploadFileUtils();
		ValueChecker vc = new ValueChecker();
		
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
}
