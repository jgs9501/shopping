package com.junsoo.shopping.common.service.user;

import java.util.HashMap;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.user.UserpointDAO;
import com.junsoo.shopping.common.vo.UserVO;

@Service
@Transactional
public class UserpointServiceImpl implements UserpointService{

	private static final Logger logger = LoggerFactory.getLogger(UserpointService.class);
	
	@Inject
	UserpointDAO userpointDAO;
	
	
	@Override
	public int selectCntUserPointId(String user_id) throws Exception {
		try {
			if(user_id == "" || user_id.length() < 5) {
				return -1;
			}
			
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return -3;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -4;
		}
		return userpointDAO.selectCountUserId(user_id);
	}

	@Override
	public int selectUserPoint(String user_id) throws Exception {
		try {
			if(user_id == "" || user_id.length() < 5) {
				return -1;
			}
			if(userpointDAO.selectCountUserId(user_id) != 1) {
				return -1;
			}
			
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return -3;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -4;
		}
		return userpointDAO.selectUserPoint(user_id);
	}

	@Override
	public void insertUserPoint(UserVO userVO) throws Exception {
		try {
			if(userVO.getUser_id() == "" ||
			   userVO.getUser_id().length() < 5) {
				return;
			}
			userpointDAO.insertUserPoint(userVO);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return;
		}
	}

	@Override
	public int updateUserPoint(HashMap<String, Object> userPointMap, String pay_type) throws Exception {
		try {
			logger.info("payment process.. pay_type : " + pay_type);
			String user_id = (String)userPointMap.get("user_id");
			// 사용 포인트 값
			int usePoint = (int)userPointMap.get("point");
			// 결제 포인트 값
			int totalPrice = (int)userPointMap.get("totalPrice");
			// 현재 포인트 값
			int currentPoint = selectUserPoint(user_id);
			// 최종 포인트
			int updatePoint = currentPoint;
			
			// 유저의 아이디 존재 확인
			if(userpointDAO.selectCountUserId(user_id) != 1) {
				return -1;
			}
			// 현재 갖고있는 포인트가 사용할 포인트보다 적을 경우 -2 반환
			if(currentPoint < usePoint) {
				return -2;
			}
			// 포인트로만 결제 시, 총 가격과 같은지 확인
			if(pay_type == "point" && usePoint == totalPrice) {
				return -2;
			}
			updatePoint = currentPoint - usePoint;
			userPointMap.replace("point", updatePoint);
			userpointDAO.updateUserPoint(userPointMap);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return -3;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -4;
		}
		return 1;
	}

	@Override
	public int deleteUserPoint(String user_id) throws Exception {
		try {
			if(userpointDAO.selectCountUserId(user_id) != 1) {
				return -1;
			}
			userpointDAO.deleteUserPoint(user_id);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return -3;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -4;
		}
		return 1;
	}

	/**
	 * 테스트용 보유포인트 추가하는 메소드
	 */
	@Override
	public int updateTestPoint(HashMap<String, Object> userPointMap) throws Exception {
		
		try {
			String user_id = (String)userPointMap.get("user_id");
			// 현재 포인트 값
			int currentPoint = selectUserPoint(user_id);
			// 최종 포인트
			int updatePoint = currentPoint;
			
			// 유저의 아이디 존재 확인
			if(userpointDAO.selectCountUserId(user_id) != 1) {
				return -1;
			}
			updatePoint += (int)userPointMap.get("point");
			userPointMap.replace("point", updatePoint);
			userpointDAO.updateUserPoint(userPointMap);
			} catch (NullPointerException npe) {
				logger.error(npe.getMessage());
				return -3;
			} catch (Exception e) {
				logger.error(e.getMessage());
				return -4;
			}
			return 1;
		}
	
}
