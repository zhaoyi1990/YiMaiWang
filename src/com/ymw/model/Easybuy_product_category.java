package com.ymw.model;


/**
 * 商品类别
 * */
public class Easybuy_product_category {
	
	private Integer epc_id;
	private String epc_name;
	private Integer epc_parent_id;
	public Integer getEpc_id() {
		return epc_id;
	}
	public void setEpc_id(Integer epc_id) {
		this.epc_id = epc_id;
	}
	public String getEpc_name() {
		return epc_name;
	}
	public void setEpc_name(String epc_name) {
		this.epc_name = epc_name;
	}
	public Integer getEpc_parent_id() {
		return epc_parent_id;
	}
	public void setEpc_parent_id(Integer epc_parent_id) {
		this.epc_parent_id = epc_parent_id;
	}
	@Override
	public String toString() {
		return "Easybuy_product_category [epc_id=" + epc_id + ", epc_name=" + epc_name + ", epc_parent_id="
				+ epc_parent_id + "]";
	}
	
	/** Easybuy_product_category构造器 参数为父类别ID */
	public Easybuy_product_category(Integer epc_parent_id) {
		super();
		this.epc_parent_id = epc_parent_id;
	}
	public Easybuy_product_category() {
		super();
	}
	
	
}
