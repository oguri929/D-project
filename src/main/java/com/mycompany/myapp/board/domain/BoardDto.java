package com.mycompany.myapp.board.domain;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("boardDto")
public class BoardDto {
	private int num;
	private String title;
	private String writer;
	private String content;
	private int cnt;
	private Date regdate;

	public BoardDto() {}

	public BoardDto(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	
	
	
	
	
	
}
