package com.Howard.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 客户 用于测试一对多
 * @author Howard
 * 2017年3月1日
 */
public class Customer {
	private Integer cid;
	private String custName;
	private String custLevel;
	
	//1对多配置 
	//1个客户对多个联系人
	//用set可以包证不重复
	private Set<LinkMan> linkMans = new HashSet<LinkMan>();
	
	public Set<LinkMan> getLinkMans() {
		return linkMans;
	}
	public void setLinkMans(Set<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", custName=" + custName + ", custLevel=" + custLevel + "]";
	}
	
}
