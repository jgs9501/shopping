package com.junsoo.shopping.common.service.contact;

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

import com.junsoo.shopping.common.dao.contact.NoticeDAO;
import com.junsoo.shopping.common.vo.NoticeVO;
import com.junsoo.shopping.utils.UploadFileUtils;
import com.junsoo.shopping.utils.checker.ValueChecker;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

	private static final Logger logger = LoggerFactory.getLogger(NoticeService.class);
	private static final int NOTICE_IMAGE_SIZE = 3;
	
	@Inject
	NoticeDAO noticeDAO;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@Override
	public int selectAllNoticeCount() throws Exception {
		
		try {
			return noticeDAO.selectAllNoticeCount();
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
	public int selectTypeNoticeCount(String type) throws Exception {
		
		ValueChecker checker = new ValueChecker();
		try {
			if(!checker.isExistContactType(type)) {
				logger.error("type does not exist");
				return -1;
			}
			return noticeDAO.selectTypeNoticeCount(type);
			
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
	public NoticeVO selectNotice(int notice_id) throws Exception {
		
		NoticeVO noticeVO = new NoticeVO();
		ValueChecker vc = new ValueChecker();
		try {
			if(notice_id < 1) {
				logger.error("notice_id does not exist");
				return null;
			}
			noticeVO = noticeDAO.selectNotice(notice_id);
			noticeVO.setType(vc.getContactTypeToName(noticeVO.getType()));
			return noticeVO;
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
	public NoticeVO selectUpdateNotice(int notice_id) throws Exception {
		
		NoticeVO noticeVO = new NoticeVO();
		ValueChecker vc = new ValueChecker();
		try {
			if(notice_id < 1) {
				logger.error("notice_id does not exist");
				return null;
			}
			noticeVO = noticeDAO.selectNotice(notice_id);
			noticeVO.setType(vc.getContactTypeToName(noticeVO.getType()));
			noticeVO.setContent(noticeVO.getContent().replaceAll("<br>", "\n"));
			return noticeVO;
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
	public List<NoticeVO> selectAllNoticePaging(HashMap<String, Object> hashMap) throws Exception {
		
		List<NoticeVO> noticeVO_list = null;
		ValueChecker vc = new ValueChecker();
		try {
			
			noticeVO_list = noticeDAO.selectAllNoticePaging(hashMap);
			for(NoticeVO vo : noticeVO_list) {
				vo.setType(vc.getContactTypeToName(vo.getType()));
			}
			return noticeVO_list;
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
	public List<NoticeVO> selectAllNotice() throws Exception {
		
		List<NoticeVO> noticeVO_list = null;
		ValueChecker vc = new ValueChecker();
		try {
			
			noticeVO_list = noticeDAO.selectAllNotice();
			for(NoticeVO vo : noticeVO_list) {
				vo.setType(vc.getContactTypeToName(vo.getType()));
			}
			return noticeVO_list;
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
	public List<NoticeVO> selectTypeNotice(String type) throws Exception {
		
		List<NoticeVO> noticeVO_list = null;
		ValueChecker vc = new ValueChecker();
		try {
			if(!vc.isExistContactType(type)) {
				logger.error("type does not exist");
				return null;
			}
			
			noticeVO_list = noticeDAO.selectTypeNotice(type);
			for(NoticeVO vo : noticeVO_list) {
				vo.setType(vc.getContactTypeToName(vo.getType()));
			}
			return noticeVO_list;
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
	public int insertNotice(NoticeVO noticeVO, 
							List<MultipartFile> uploadFile_list) throws Exception {

		UploadFileUtils uploadFileUtils = new UploadFileUtils();
		ValueChecker checker = new ValueChecker();
		
		String imgUploadPath = uploadPath + File.separator + "images";
		String ymdPath = uploadFileUtils.calcPath(imgUploadPath);
		String[] fileName = new String[NOTICE_IMAGE_SIZE];
		int index = 0;
		
		try {
			// 공지사항의 상담종류 존재 확인
			// 해당 항목이 없을 경우, 기타항목으로 설정
			if(!checker.isExistContactType(noticeVO.getType())) {
				logger.warn("The type does not exist, so register it as an ETC.");
				noticeVO.setType("ETC");
			}
			
			// 공지사항 제목 입력확인
			if(noticeVO.getTitle().isEmpty() || noticeVO.getTitle() == "") {
				logger.error("Does not exist title. noticeVO : " + noticeVO);
				return -1;
			}
			
			// 공지사항 textarea 줄바꿈 치환
			if(!noticeVO.getContent().isEmpty()) {
				noticeVO.setContent(noticeVO.getContent().replaceAll("\n", "<br>"));
			}
			
			// 파일 업로드 폴더 생성 및 파일 저장
			for(MultipartFile file : uploadFile_list) {
				if(!file.isEmpty()) {
					fileName[index] = File.separator + "images" + ymdPath + File.separator +
										uploadFileUtils.fileUpload(imgUploadPath, 
															file.getOriginalFilename(), 
															file.getBytes(),
															ymdPath,
															false);
				} else {
					fileName[index] = null;
				}
				index++;
			}
			
			noticeVO.setContent_img1(fileName[0]);
			noticeVO.setContent_img2(fileName[1]);
			noticeVO.setContent_img3(fileName[2]);
			
			noticeDAO.insertNotice(noticeVO);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return 400;
		} catch (Exception e) {
			logger.error(e.getMessage() + " " + e.getLocalizedMessage());
			return 401;
		}
		
		return 200;
	}

	@Override
	public int updateNotice(NoticeVO noticeVO, List<MultipartFile> uploadFile_list) throws Exception {
		
		UploadFileUtils uploadFileUtils = new UploadFileUtils();
		ValueChecker checker = new ValueChecker();
		
		String imgUploadPath = uploadPath + File.separator + "images";
		String ymdPath = uploadFileUtils.calcPath(imgUploadPath);
		String[] fileName = new String[NOTICE_IMAGE_SIZE];
		int index = 0;
		
		try {
			// 공지사항의 상담종류 존재 확인
			// 해당 항목이 없을 경우, 기타항목으로 설정
			if(!checker.isExistContactType(noticeVO.getType())) {
				logger.warn("The type does not exist, so register it as an ETC.");
				noticeVO.setType("ETC");
			}
			// 공지사항 공유의 값 확인 
			if(noticeVO.getNotice_id() < 1) {
				logger.error("Does not exist notice. : " + noticeVO);
				return 501;
			}
			// 공지사항 제목 입력확인
			if(noticeVO.getTitle().isEmpty() || noticeVO.getTitle() == "") {
				logger.error("Notice title error. : " + noticeVO);
				return 502;
			}
			// 공지사항 내용 입력확인
			if(noticeVO.getContent().isEmpty() || noticeVO.getContent() == "") {
				logger.error("Notice content error. : " + noticeVO);
				return 503;
			}
			
			for(MultipartFile file : uploadFile_list) {
				if(!file.isEmpty()) {
					fileName[index] = File.separator + "images" + ymdPath + File.separator +
										uploadFileUtils.fileUpload(imgUploadPath, 
															file.getOriginalFilename(), 
															file.getBytes(),
															ymdPath,
															false);
				} else {
					fileName[index] = null;
				}
				index++;
			}
			
			noticeVO.setContent(noticeVO.getContent().replaceAll("\n", "<br>"));
			// 이미지를 바꾼 경우 해당 이미지 DB컬럼 수정 
			if(fileName[0] != null) { noticeVO.setContent_img1(fileName[0]);}
			if(fileName[1] != null) { noticeVO.setContent_img2(fileName[1]);}
			if(fileName[2] != null) { noticeVO.setContent_img3(fileName[2]);}
			
			noticeDAO.updateNotice(noticeVO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 500;
		}
		return 200;
	}

	@Override
	public int updateViewsNotice(NoticeVO noticeVO) throws Exception {
		try {
			if(noticeVO.getNotice_id() < 1) {
				logger.error("Does not exist notice. : " + noticeVO);
				return 501;
			}
			noticeDAO.updateViewsNotice(noticeVO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 500;
		}
		return noticeVO.getViews()+1;
	}

	@Override
	public int deleteNotice(int notice_id) throws Exception {
		
		try {
			if(notice_id < 1) {
				logger.error("Does not exist notice. notice_id : " + notice_id);
				return 501;
			}
			logger.info("Deleted the notice. notice_id : " + notice_id);
			noticeDAO.deleteNotice(notice_id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 500;
		}
		return 200;
	}

}
