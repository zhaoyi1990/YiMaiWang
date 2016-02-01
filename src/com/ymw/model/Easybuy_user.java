package com.ymw.model;

import java.util.Date;
/**
 * ”√ªß
 * */
public class Easybuy_user {
	private Integer eu_user_id;
	private String eu_user_name;
	private String eu_name;
	private String eu_password;
	private Integer eu_sex;
	private Date eu_birthday;
	private String eu_identity_code;
	private String eu_email;
	private String eu_mobile;
	private String eu_address;
	private Integer eu_status;
	public Integer getEu_user_id() {
		return eu_user_id;
	}
	public void setEu_user_id(Integer eu_user_id) {
		this.eu_user_id = eu_user_id;
	}
	public String getEu_user_name() {
		return eu_user_name;
	}
	public void setEu_user_name(String eu_user_name) {
		this.eu_user_name = eu_user_name;
	}
	public String getEu_name() {
		return eu_name;
	}
	public void setEu_name(String eu_name) {
		this.eu_name = eu_name;
	}
	public String getEu_password() {
		return eu_password;
	}
	public void setEu_password(String eu_password) {
		this.eu_password = eu_password;
	}
	public Integer getEu_sex() {
		return eu_sex;
	}
	public void setEu_sex(Integer eu_sex) {
		this.eu_sex = eu_sex;
	}
	public Date getEu_birthday() {
		return eu_birthday;
	}
	public void setEu_birthday(Date eu_birthday) {
		this.eu_birthday = eu_birthday;
	}
	public String getEu_identity_code() {
		return eu_identity_code;
	}
	public void setEu_identity_code(String eu_identity_code) {
		this.eu_identity_code = eu_identity_code;
	}
	public String getEu_email() {
		return eu_email;
	}
	public void setEu_email(String eu_email) {
		this.eu_email = eu_email;
	}
	public String getEu_mobile() {
		return eu_mobile;
	}
	public void setEu_mobile(String eu_mobile) {
		this.eu_mobile = eu_mobile;
	}
	public String getEu_address() {
		return eu_address;
	}
	public void setEu_address(String eu_address) {
		this.eu_address = eu_address;
	}
	public Integer getEu_status() {
		return eu_status;
	}
	public void setEu_status(Integer eu_status) {
		this.eu_status = eu_status;
	}
	@Override
	public String toString() {
		return "Easybuy_user [eu_user_id=" + eu_user_id + ", eu_user_name=" + eu_user_name + ", eu_name=" + eu_name
				+ ", eu_password=" + eu_password + ", eu_sex=" + eu_sex + ", eu_birthday=" + eu_birthday
				+ ", eu_identity_code=" + eu_identity_code + ", eu_email=" + eu_email + ", eu_mobile=" + eu_mobile
				+ ", eu_address=" + eu_address + ", eu_status=" + eu_status + "]";
	}
	
}
