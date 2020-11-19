package com.junsoo.shopping.common.service.product;

import java.io.File;

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

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Inject
	ProductDAO productDAO;

	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@Override
	public int insertProduct(ProductVO productVO, MultipartFile file) throws Exception {
		
		logger.info("insertProduct method called");
		String imgUploadPath = uploadPath + File.separator + "images";
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;
		
		try {
			if(file != null) {
				fileName = UploadFileUtils.fileUpload(imgUploadPath, 
						file.getOriginalFilename(), 
						file.getBytes(),
						ymdPath);
			} else {
				fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
			}
			productVO.setProduct_img(File.separator + "images" + ymdPath + File.separator + fileName);
			productVO.setProduct_thumbImg(File.separator + "images" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		
			if(productVO.getSeq_user_id() < 1 ||
				productVO.getProduct_name() == null ||
				productVO.getProduct_cnt() < 0 ||
				productVO.getProduct_desc() == null ||
				productVO.getProduct_price() < 0 ||
				productVO.getSale() == '\u0000' ||
				productVO.getDiscount() < 0 ||
				productVO.getCategory() < 1) {
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
	

}
