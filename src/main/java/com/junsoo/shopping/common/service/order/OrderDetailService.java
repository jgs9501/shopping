package com.junsoo.shopping.common.service.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.junsoo.shopping.common.vo.OrderDetailVO;
import com.junsoo.shopping.common.vo.OrderVO;

/**
 * 주문/결제 상세 메소드<br>
 * 결제할 경우 모든 상품관련 비즈니스 처리 메소드
 * @author jjsoo
 *
 */
public interface OrderDetailService {
	/**
	 * 주문/결제한 모든 상품 조회
	 * @param seq_user_id
	 * @return
	 * @throws Exception
	 */
	public List<OrderDetailVO> selectAllOrderDetail(int seq_user_id) throws Exception;
	
	/**
	 * 주문/결제시 order_id와 seq_user_id로 상세주문조회
	 * @param orderVO
	 * @return
	 * @throws Exception
	 */
	public ArrayList<OrderDetailVO> selectOrderDetail(OrderVO orderVO) throws Exception;
	
	/**
	 * 주문/결제시 동일한 order_id와 seq_user_id로 주문목록 조회
	 * @param orderDetailVO
	 * @return
	 * @throws Exception
	 */
	public OrderDetailVO selectOrderDetail(OrderDetailVO orderDetailVO) throws Exception;

	/**
	 * 주문/결제시 동일한 order_id와 seq_user_id로 주문목록 조회
	 * @param orderDetailVO
	 * @return
	 * @throws Exception
	 */
	public ArrayList<OrderDetailVO> selectListOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	
	/**
	 * 주문/결제시 상세주문 테이블 입력
	 * @param orderDetailVO
	 * @return
	 * @throws Exception
	 */
	public int insertOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	
	/**
	 * 상세주문 id 갱신
	 * @param orderDetailVO
	 * @return
	 * @throws Exception
	 */
	public int updateOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	
	/**
	 * order_id가 같은 상세주문 삭제
	 * @param orderDetailVO
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	
	/**
	 * 특정 상세주문만 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderDetail(HashMap<String, Object> map) throws Exception;
}
