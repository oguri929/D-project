package com.mycompany.myapp.chat.domain;

import org.apache.ibatis.type.Alias;

@Alias("chatVO")
public class ChatVO {
	private int num;
	private String content;
	private String filename;
	
	public ChatVO() {
		
	}
	
	public ChatVO(int num, String content, String filename) {
		this.num = num;
		this.content = content;
		this.filename = filename;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
