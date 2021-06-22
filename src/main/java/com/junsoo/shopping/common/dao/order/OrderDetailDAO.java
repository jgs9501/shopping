package com.junsoo.shopping.common.dao.order;

import java.util.HashMap;
import java.util.List;

import com.junsoo.shopping.common.vo.OrderDetailVO;
import com.junsoo.shopping.common.vo.OrderVO;

/**
 * order_detail 테이블의 SQL호출 메소드
 * @author jjsoo
 *
 */
public interface OrderDetailDAO {

	/**
	 * order_detail 테이블 모든 주문상품 조회 메소드
	 * @param seq_user_id
	 * @return
	 * @throws Exception
	 */
	public List<OrderDetailVO> selectAllOrderDetail(int seq_user_id) throws Exception;
	
	/**
	 * order_detail 테이블 특정 주문번호로 조회 메소드
	 * @param orderVO
	 * @return
	 * @throws Exception
	 */
	public List<OrderDetailVO> selectOrderDetail(OrderVO orderVO) throws Exception;
	
	/**
	 * order_detail 테이블 특정 주문번호로 조회 메소드
	 * @param orderDetailVO
	 * @return
	 * @throws Exception
	 */
	public List<OrderDetailVO> selectOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	
	/**
	 * order_detail 테이블 주문한 상품 수량 조회 메소드
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int selectCntOrderDetail(HashMap<String, Object> map) throws Exception;
	
	/**
	 * order_detail 테이블 주문/결제완료 시 각 상품마다 입력하는 메소드
	 * @param orderDetailVO
	 * @throws Exception
	 */
	public void insertOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	
	/**
	 * order_detail 테이블 주문번호 갱신 메소드
	 * @param orderDetailVO
	 * @throws Exception
	 */
	public void updateOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	
	/**
	 * order_detail 테이블 주문번호 삭제 메소드
	 * @param orderDetailVO
	 * @throws Exception
	 */
	public void deleteOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	
	/**
	 * order_detail 테이블 주문번호 삭제 메소드
	 * @param map
	 * @throws Exception
	 */
	public void deleteOrderDetail(HashMap<String, Object> map) throws Exception;
}
