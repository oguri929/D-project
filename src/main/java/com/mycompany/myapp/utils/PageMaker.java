package com.mycompany.myapp.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalCount;	//전체 게시글 수
	private int startPage;	//페이지 리스트 시작 페이지 번호
	private int endPage;	//페이지 리스트 끝 페이지 번호
	private boolean prev;	//이전 버튼
	private boolean next;	//다음 버튼
	private int displayPageNum = 10;	//보여줄 페이지 수(기본: 10페이지로 설정)
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	//전체 게시글 수 받아오면 페이지 리스트 계산해서 값 설정하기
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public boolean isPrev() {
		return prev;
	}
	
	public boolean isNext() {
		return next;
	}
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	public Criteria getCri() {
		return cri;
	}
	//시작 페이지 번호, 끝 페이지 번호, 이전버튼, 이후버튼 활성화 여부 결정하기 
	private void calcData() {
		//((현재 페이지 번호/보여줄 페이지 수)*보여줄 페이지 수) = 끝 페이지 번호
		endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		//((끝 페이지 번호 - 보여줄 페이지 수) + 1 = 시작 페이지
		startPage = (endPage - displayPageNum) + 1;
		
		//현재 페이지 번호가 10이하인 경우를 대비하여
		//(전체 게시글 수/페이지당 게시글 수) = 임시 끝 페이지 번호
		int tempEndPage = (int) (Math.ceil(totalCount / (double)cri.getPerPageNum()));
		//끝 페이지 번호 > 임시 끝 페이지 번호 -> 임시 끝 페이지 번호를 끝 페이지 번호에 대입
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		//시작 페이지 번호가 1이 아닌 경우 prev 버튼 활성화
		prev = startPage == 1 ? false : true;
		//(마지막 페이지 번호*페이지 당 게시글 수) < 전체 게시글 수 일 때 next버튼 활성화
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}
	
	public String makeQuery(int page) {
		UriComponents uriComponents =
		UriComponentsBuilder.newInstance()
						    .queryParam("page", page)
							.queryParam("perPageNum", cri.getPerPageNum())
							.build();
		   
		return uriComponents.toUriString();
	}
	
	// page, perPageNum, searchType, keyword를 url로 사용
	public String makeSearch(int page){
	  
	 UriComponents uriComponents =
	            UriComponentsBuilder.newInstance()
	            .queryParam("page", page)
	            .queryParam("perPageNum", cri.getPerPageNum())
	            .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
	            .queryParam("keyword", encoding(((SearchCriteria)cri).getKeyword()))
	            .build(); 
	    return uriComponents.toUriString();  
	}
	
	private String encoding(String keyword) {
		if(keyword == null || keyword.trim().length() == 0) { 
			return "";
		}
		 
		try {
			return URLEncoder.encode(keyword, "UTF-8");
		} catch(UnsupportedEncodingException e) { 
			return ""; 
		}
	}

}
