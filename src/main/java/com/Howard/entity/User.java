package com.Howard.entity;
/**
 * 实体类 
 * 一般属性建议用包装类 如：int用Integer
 * @author Howard
 * 2017年2月28日
 */
public class User {
	
//	private String uid;
	private int uid;
	private String username;
	private String address;
//	public String getUid() {
//		return uid;
//	}
//	public void setUid(String uid) {
//		this.uid = uid;
//	}
	public String getUsername() {
		return username;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", address=" + address + "]";
	}
}
