package com.primeton.zhangzezhao.demo.entity;

import java.io.Serializable;

/**
 * 组织实体类
 */
public class Org implements Serializable {

	private static final long serialVersionUID = -2718843291261591526L;

	private Integer orgId;
	private Integer orgNo;
	private String orgName;
	private Integer leaderOrg;
	private String orgLoc;
	private String leaderOrgName;

	public String getLeaderOrgName() {
		return leaderOrgName;
	}

	public void setLeaderOrgName(String leaderOrgName) {
		this.leaderOrgName = leaderOrgName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(Integer orgNo) {
		this.orgNo = orgNo;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getLeaderOrg() {
		return leaderOrg;
	}

	public void setLeaderOrg(Integer leaderOrg) {
		this.leaderOrg = leaderOrg;
	}

	public String getOrgLoc() {
		return orgLoc;
	}

	public void setOrgLoc(String orgLoc) {
		this.orgLoc = orgLoc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
