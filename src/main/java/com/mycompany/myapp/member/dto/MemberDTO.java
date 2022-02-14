package com.mycompany.myapp.member.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Repository;

@Alias("memberDTO")
@Repository
public class MemberDTO {
	private int num;
	private String id;
	private String pw;
	private String local;
	private Date birth;
	private String gender;
	private String nickname;
	private String email;
	private int admin;
	private String name;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "MemberDTO [num=" + num + ", id=" + id + ", pw=" + pw + ", local=" + local + ", birth=" + birth
				+ ", gender=" + gender + ", nickname=" + nickname + ", email=" + email + ", admin=" + admin + ", name="
				+ name + "]";
	}
	
	
	
	

}
