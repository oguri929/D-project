package com.mycompany.myapp.utils;

public class SearchCriteria extends Criteria {
	private String searchType = "";
	private String keyword = ""; 
	private int subjNum;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getSubjNum() {
		return subjNum;
	}
	public void setSubjNum(int subjNum) {
		this.subjNum = subjNum;
	}
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	
	
}
