package org.tester.chatting.room.domain;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("roomVO")
public class RoomVO {
	private int code;
	private String password;
	private String local;
	private String roomname;
	private String roomdiscript;
	private String memberlimit;
	private Timestamp regdate;
	private int captain;
	private int subjectnum;
	
	public RoomVO() {
		
	}
	
	public RoomVO(int code, String password, String local, String roomname, String roomdiscript, 
			String memberlimit,Timestamp regdate, int captain, int subjectnum) {
		this.code = code;
		this.password = password;
		this.local = local;
		this.roomname = roomname;
		this.roomdiscript = roomdiscript;
		this.memberlimit = memberlimit;
		this.regdate = regdate;
		this.captain = captain;
		this.subjectnum = subjectnum;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public String getRoomdiscript() {
		return roomdiscript;
	}

	public void setRoomdiscript(String roomdiscript) {
		this.roomdiscript = roomdiscript;
	}

	public String getMemberlimit() {
		return memberlimit;
	}

	public void setMemberlimit(String memberlimit) {
		this.memberlimit = memberlimit;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public int getCaptain() {
		return captain;
	}

	public void setCaptain(int captain) {
		this.captain = captain;
	}

	public int getSubjectnum() {
		return subjectnum;
	}

	public void setSubjectnum(int subjectnum) {
		this.subjectnum = subjectnum;
	}
}
