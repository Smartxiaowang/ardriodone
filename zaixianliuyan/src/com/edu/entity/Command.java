package com.edu.entity;

import java.io.Serializable;

public class Command implements Serializable{
	private int cId;
	private int uId;
	private int mId;
	private String cWord;
	private String cTime;
	private String uName;
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getcWord() {
		return cWord;
	}
	public void setcWord(String cWord) {
		this.cWord = cWord;
	}
	public String getcTime() {
		return cTime;
	}
	public void setcTime(String cTime) {
		this.cTime = cTime;
	}	
}
