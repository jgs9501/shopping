package com.junsoo.shopping.common.service.product;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.junsoo.shopping.common.vo.ProductVO;

/**
 * 상품관련 비즈니스처리 메소드
 * @author jjsoo
 *
 */
public interface ProductService {

	/**
	 * 최근 출시한 상품 조회 메소드
	 * @param category
	 * @return
	 * @throws Exception
	 */
	public List<ProductVO> selectRecentlyProduct(int category) throws Exception;

	/**
	 * 해당 카테고리의 상품 개수 조회 메소드
	 * @param hashMap
	 * @return
	 * @throws Exception
	 */
	public int selectCategoryProductCount(HashMap<String, Object> hashMap) throws Exception;
	
	/**
	 * 해당 카테고리의 상품 조회 메소드
	 * @param productVO
	 * @param paginationInfo
	 * @return
	 * @throws Exception
	 */
	public List<ProductVO> selectCategoryProducts(HashMap<String, Object> hashMap) throws Exception;
	
	/**
	 * 고객의 특정 상품 구매여부 및 작성리뷰 조회 메소드
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> selectBuyProduct(Map<String, Object> hashMap) throws Exception;
	
	/**
	 * 고객이 구매한 모든 상품 조회 메소드
	 * @param seq_user_id
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> selectBuyProducts(int seq_user_id) throws Exception;
	
	/**
	 * 점포의 상품 출판 메소드
	 * @param productVO
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public int insertProduct(ProductVO productVO, MultipartFile file) throws Exception;
	
	/**
	 * 점포가 출판한 상품 수정 메소드
	 * @param productVO
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public int updateProduct(ProductVO productVO, MultipartFile file) throws Exception;
}
