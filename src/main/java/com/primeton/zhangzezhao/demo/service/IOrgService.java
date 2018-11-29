package com.primeton.zhangzezhao.demo.service;

import java.util.List;

import com.primeton.zhangzezhao.demo.entity.Org;

public interface IOrgService {
	
	/**
	 * 根据部门id获取部门信息
	 * @param orgId 部门id
	 * @return 查询到的部门对象
	 */
	public Org getOrgByOrgId(Integer orgId);
	
	/**
	 * 查询所有的部门
	 * @return 所有部门的集合
	 */
	public List<Org> queryAllOrgs();
	
	
	/**
	 * 根据部门名获取部门信息
	 * @param orgName 部门名称
	 * @return 获取到的部门对象
	 */
	public Org getOrgByOrgName(String orgName);
	
	/**
	 * 添加部门
	 * @param org  部门数据存放的对象
	 * @return  添加成功的部门对象
	 */
	public Org createOrg(Org org);
	
	/**
	 * 根据部门id删除部门
	 * @param orgId 部门id
	 * @return 受影响的行数
	 */
	public Integer removeOrg(Integer orgId);
	
	/**
	 * 修改部门信息
	 * @param org 要修改的部门对象
	 * @return 受影响的行数
	 */
	public Integer modifyOrg(Org org);
	
	/**
	 * 根据上级部门号查询下属部门
	 * @param leaderOrg
	 * @return
	 */
	public List<Org> getOrgByLeaderOrg(Integer leaderOrg);
	
	/**
	 * 模糊查询组织
	 * @param orgName 组织名
	 * @return
	 */
	public List<Org> getOrgByBlurOrgName(String orgName);
	
}
