package com.Howard.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 雇员
 * 测试多对多
 * @author Howard
 * 2017年3月1日
 */
public class Employe {
	private Integer e_id;
	private String e_name;
	private String e_address;
	
	private Set<Role> roles = new HashSet<Role>();
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Integer getE_id() {
		return e_id;
	}
	public void setE_id(Integer e_id) {
		this.e_id = e_id;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	public String getE_address() {
		return e_address;
	}
	public void setE_address(String e_address) {
		this.e_address = e_address;
	}
	@Override
	public String toString() {
		return "Employe [e_id=" + e_id + ", e_name=" + e_name + ", e_address=" + e_address + "]";
	}
	
}
