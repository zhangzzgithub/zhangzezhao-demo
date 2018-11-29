package com.primeton.zhangzezhao.demo.entity;

import java.io.Serializable;

/**
 * 用户实体类
 */
public class User implements Serializable{

	private static final long serialVersionUID = 5523439784111440387L;
	
	private Integer userId;
	private String userName;
	private String password;
	private Integer orgNo;
	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public User() {
		super();
	}

	public User(Integer userId, String userName, String password, Integer orgNo) {
		super();
		setUserId(userId);
		setUserName(userName);
		setPassword(password);
		setOrgNo(orgNo);
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(Integer orgNo) {
		this.orgNo = orgNo;
	}
	
}
