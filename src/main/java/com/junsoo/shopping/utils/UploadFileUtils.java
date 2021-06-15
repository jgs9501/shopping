package com.junsoo.shopping.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

import net.coobird.thumbnailator.Thumbnails;


public class UploadFileUtils {
	
	static final int THUMBNAIL_IMAGE_WIDTH = 240;
	static final int THUMBNAIL_IMAGE_HEIGHT = 240;
	
	/**
	 * 파일 업로드할 때 사용하는 메소드
	 * @param uploadPath
	 * @param fileName
	 * @param fileData
	 * @param ymdPath
	 * @param thumbFile_flag 
	 * @return
	 * @throws Exception
	 */
	public String fileUpload(String uploadPath, 
									String fileName,
									byte[] fileData, 
									String ymdPath,
									boolean thumbFile_flag) throws Exception {
		
		UUID uid = UUID.randomUUID();
		
		String newFileName = uid + "_" + fileName;
		String thumbFileName = "s_" + newFileName;
		String imgPath = uploadPath + ymdPath;
		
		File target = new File(imgPath, newFileName);
		FileCopyUtils.copy(fileData, target);
		
		File image = new File(imgPath + File.separator + newFileName);
		
		// 썸네일 파일 생성할 경우 실행
		if(thumbFile_flag && image.exists()) {
			File thumbnail = new File(imgPath + File.separator + "s" + File.separator + thumbFileName);
			thumbnail.getParentFile().mkdirs();
			Thumbnails.of(image).size(THUMBNAIL_IMAGE_WIDTH, THUMBNAIL_IMAGE_HEIGHT).toFile(thumbnail);
		}
		
		return newFileName;
	}
	
	public String calcPath(String uploadPath) {
		
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		makeDir(uploadPath, yearPath, monthPath, datePath + "\\s");
		
		return datePath;
	}
	
	private void makeDir(String uploadPath, String... paths) {
		
		if(new File(paths[paths.length - 1]).exists()) { return; }
		
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			
			if(!dirPath.exists()) {
				dirPath.mkdirs();
			}
		}
	}
}
