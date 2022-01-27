package com.mycompany.myapp.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycompany.myapp.board.domain.BoardDto;

@Component("fileManager")
public class FileManager {
	private static final String filePath = "C:\\board\\file"; // 파일이 저장될 위치

	public List<Map<String, Object>> parseInsertFileInfo(BoardDto boardDto, MultipartHttpServletRequest mpRequest)
			throws Exception {

		/*
		 * Iterator은 데이터들의 집합체? 에서 컬렉션으로부터 정보를 얻어올 수 있는 인터페이스입니다. List나 배열은 순차적으로 데이터의
		 * 접근이 가능하지만, Map등의 클래스들은 순차적으로 접근할 수가 없습니다. Iterator을 이용하여 Map에 있는 데이터들을
		 * while문을 이용하여 순차적으로 접근합니다.
		 */

		Iterator<String> iterator = mpRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;

		int boardNum = boardDto.getNum();

		// 임시 파일 경로 생성
		File file = new File(filePath + "\\" + "temp");
		if (!file.exists()) {
			file.mkdirs();
		}

		while (iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if (!multipartFile.isEmpty()) {
				// 파일명 변경
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;

				// 임시폴더에 저장한 파일을 게시판 번호명으로 생성된 폴더로 옮겨주기				
			    File srcFile = new File(filePath + "\\" + "temp" + "\\" + storedFileName);
			    multipartFile.transferTo(srcFile); 
			    File destDir = new File(filePath + "\\" + boardNum); 
			    destDir.mkdir(); 
			    FileUtils.moveFileToDirectory(srcFile, destDir, true);
				 

				// 파일 정보 listMap에 담은 뒤 list타입으로 반환
				listMap = new HashMap<String, Object>();
				listMap.put("BOARD_NUM", boardNum);
				listMap.put("ORG_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);
			}
		}
		return list;
	}

	// 32글자의 랜덤한 문자열(숫자포함)을 만들어서 반환해주는 기능
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}