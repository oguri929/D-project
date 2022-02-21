package com.mycompany.myapp.utils;

import org.apache.ibatis.type.Alias;

@Alias("criteria")
public class Criteria {
	private int page;	//현재 페이지 번호(기본 : 1페이지)
	private int perPageNum;	//페이지 당 게시글 수(기본 : 10개)
	private int rowStart;	//DB에서 검색 시 사용, 시작 행
	private int rowEnd;		//DB에서 검색 시 사용, 마지막 행
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	//현재 페이지가 0과 같거나 작다면 1로 설정하기
	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	//페이지 당 게시글 수가 0보다 작거나 100보다 크면 10으로 설정하기
	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		return page;
	}
	
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}
	
	public int getPerPageNum() {
		return this.perPageNum;
	}
	//현재 페이지, 페이지 당 게시글 수로 시작 행 설정하기 
	public int getRowStart() {
		rowStart = ((page - 1) * perPageNum) + 1;
		return rowStart;
	}
	//시작 행, 페이지 당 게시글 수로 마지막 행 설정하기
	public int getRowEnd() {
		rowEnd = rowStart + perPageNum - 1;
		return rowEnd;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd
				+ "]";
	}

}
