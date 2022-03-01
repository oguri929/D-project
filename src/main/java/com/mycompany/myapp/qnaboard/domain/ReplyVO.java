package com.mycompany.myapp.qnaboard.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.mycompany.myapp.member.dto.MemberDTO;

@Alias("replyVO")
public class ReplyVO {
	private int bno;
	private int rno;
	private String content;
	private int writer;
	private Date regdate;
	private MemberDTO memberDto;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
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
