package com.mycompany.myapp.board.domain;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import com.mycompany.myapp.member.dto.MemberDTO;

@Alias("boardDto")
public class BoardDto {
	private int num;
	private String title;
	private int writer;
	private String content;
	private int cnt;
	private Date regdate;
	private MemberDTO memberDto;

	public BoardDto() {}

	public BoardDto(String title, int writer, String content) {
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

	public int getWriter() {
		return writer;
	}

	public void setWriter(int writer) {
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

	public MemberDTO getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDTO memberDto) {
		this.memberDto = memberDto;
	}

	
	
	
	
	
	
	
}
