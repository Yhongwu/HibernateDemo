package com.Howard.entity;
/**
 * 联系人 
 * 用于测试1对多 （多）
 * @author Howard
 * 2017年3月1日
 */
public class LinkMan {

	private Integer lid;
	private String lName;
	private String lphone;
	
	//多对1配置
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getLphone() {
		return lphone;
	}
	public void setLphone(String lphone) {
		this.lphone = lphone;
	}
	@Override
	public String toString() {
		return "LinkMan [lid=" + lid + ", lName=" + lName + ", lphone=" + lphone + "]";
	}
	
}
