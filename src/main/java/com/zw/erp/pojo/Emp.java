package com.zw.erp.pojo;


import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


public class Emp {
	
	private long uuid;
	private String name;
	private String password;
	private Long gender;
	private String email;
	private String tele;
	private String address;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date birthday;

	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public long getUuid() {
		return uuid;
	}
	public void setUuid(long uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getGender() {
		return gender;
	}
	public void setGender(Long gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public java.util.Date getBirthday() {
		return birthday;
	}
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}
}
