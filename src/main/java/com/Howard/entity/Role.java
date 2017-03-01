package com.Howard.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 * 测试多对多
 * @author Howard
 * 2017年3月1日
 */
public class Role {

	private Integer r_id;
	private String r_name;
	private String r_memo;//角色描述
	
	private Set<Employe> employes = new HashSet<Employe>();
	
	public Set<Employe> getEmployes() {
		return employes;
	}
	public void setEmployes(Set<Employe> employes) {
		this.employes = employes;
	}
	public Integer getR_id() {
		return r_id;
	}
	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public String getR_memo() {
		return r_memo;
	}
	public void setR_memo(String r_memo) {
		this.r_memo = r_memo;
	}
	@Override
	public String toString() {
		return "Role [r_id=" + r_id + ", r_name=" + r_name + ", r_memo=" + r_memo + "]";
	}
	
	
}
