package com.junsoo.shopping.common.service.product;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.junsoo.shopping.common.dao.product.ProductDAO;
import com.junsoo.shopping.common.vo.ProductDetailVO;
import com.junsoo.shopping.common.vo.ProductVO;
import com.junsoo.shopping.utils.UploadFileUtils;
import com.junsoo.shopping.utils.checker.ValueChecker;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Inject
	private ProductDAO productDAO;

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
	public ProductDetailVO selectProductDetail(int product_id) throws Exception {
		
		try {
			ProductDetailVO result_productDetailVO = new ProductDetailVO();
			if(product_id < 1) {
				throw new NullPointerException();
			}
			result_productDetailVO = productDAO.selectProductDetail(product_id);
			return result_productDetailVO;
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
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
	public HashMap<String, Object> selectBuyProduct(Map<String, Object> hashMap) throws Exception {
		
		try {
			System.out.println(hashMap);
			if(!hashMap.containsKey("seq_user_id")) {
				logger.error("selectBuyProduct() seq_user_id key error. " + hashMap);
				return null;
			}
			if(!hashMap.containsKey("product_id")) {
				logger.error("selectBuyProduct() product_id key error. " + hashMap);
				return null;
			}
			return productDAO.selectBuyProduct(hashMap);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<HashMap<String, Object>> selectBuyProducts(int seq_user_id) throws Exception {

		try {
			
			if(seq_user_id < 1) {
				logger.error("selectBuyProducts() seq_user_id value error.");
				return null;
			}
			return productDAO.selectBuyProducts(seq_user_id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public ProductVO selectStoreProduct(ProductVO productVO) throws Exception {
		
		try {
			ProductVO result_productVO = new ProductVO();
			result_productVO = productDAO.selectStoreProduct(productVO);
			result_productVO.setProduct_desc(result_productVO.getProduct_desc().replaceAll("<br>", "\n"));
			return result_productVO;
		}
		catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public List<HashMap<String, Object>> selectFavoriteProduct() throws Exception {
		
		try {
			
			return productDAO.selectFavoriteProduct();
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
			
			productVO.setProduct_desc(productVO.getProduct_desc().replaceAll("\n", "<br>"));
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
		
		System.out.println(productVO);
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
			
			productVO.setProduct_desc(productVO.getProduct_desc().replaceAll("\n", "<br>"));
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
