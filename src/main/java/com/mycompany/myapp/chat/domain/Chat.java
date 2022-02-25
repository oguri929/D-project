package com.mycompany.myapp.chat.domain;

import java.sql.Date;


public class Chat{
	private int no;
	private String sender;
	private int srNo;
	private String chatContent;
	private Date sendDate;
	private String dateType;
	private String nickname;
	private String sysName;
	private String nowTime;
	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	private String vaildYN;//신고 기능 추가시 필요

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String i) {
		this.sender = i;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getVaildYN() {
		return vaildYN;
	}

	public void setVaildYN(String vaildYN) {
		this.vaildYN = vaildYN;
	}
	@Override
	public String toString() {
		String result="sender: "+this.sender+" srNo: "+this.srNo
				+" chatContent: "+this.chatContent
				+" sendDate: "+this.sendDate
				+" dateType: "+this.dateType
				+" nickname: "+this.nickname
				+" Sysname: "+this.sysName;
		return result;
	}
	
}
