package com.primeton.zhangzezhao.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeton.zhangzezhao.demo.dao.IOrgDao;
import com.primeton.zhangzezhao.demo.entity.Org;
import com.primeton.zhangzezhao.demo.exception.DemoException;
import com.primeton.zhangzezhao.demo.exception.ErrorEnum;

@Service("orgService")
public class OrgServiceImpl implements IOrgService{
	
	@Autowired
	private IOrgDao orgDao;

	//增加部门，部门名不能冲突
	@Override
	@Transactional(rollbackFor=DemoException.class)
	public Org createOrg(Org org) {
		Org data = getOrgByOrgName(org.getOrgName());
		if(data == null) {
			orgDao.insertOrg(org);
		}else {
			throw new DemoException(ErrorEnum.ORGNAME_CONFLICT);
		}
		return org;
	}
	
	@Override
	@Transactional(rollbackFor=DemoException.class)
	public Integer removeOrg(Integer orgId) {
		Integer rows = orgDao.deleteOrg(orgId);
		if(rows == 1) {
			return 1;
		}else {
			throw new DemoException(ErrorEnum.ORG_NOT_FOUND);
		}
	}
	
	@Override
	@Transactional(rollbackFor=DemoException.class)
	public Integer modifyOrg(Org org) {
		//根据部门id查询部门信息
		Org data = getOrgByOrgId(org.getOrgId());
		//如果为空，则没有此部门
		if(data==null) {
			throw new DemoException(ErrorEnum.ORG_NOT_FOUND);
		}else {
			Integer rows = orgDao.updateOrg(org);
			if(rows == 1) {
				return 1;
			}else {
				throw new DemoException(ErrorEnum.ERROR_UPDATE_ORG);
			}
		}
	}

	@Override
	public Org getOrgByOrgId(Integer orgId) {
		Org data = orgDao.getOrgByOrgId(orgId);
		if(data == null) {
			throw new DemoException(ErrorEnum.ORG_NOT_FOUND);
		}else {
			return data;
		}
	}

	@Override
	public Org getOrgByOrgName(String orgName) {
		return orgDao.getOrgByOrgName(orgName);
	}
	
	@Override
	public List<Org> queryAllOrgs() {
		return orgDao.queryAllOrgs();
	}

	@Override
	public List<Org> getOrgByLeaderOrg(Integer leaderOrg) {
		List<Org> data = orgDao.getOrgByLeaderOrg(leaderOrg);
		if(data == null) {
			throw new DemoException(ErrorEnum.ORG_NOT_FOUND);
		}else {
			return data;
		}
	}

	@Override
	public List<Org> getOrgByBlurOrgName(String orgName) {
		orgName = "%"+orgName+"%";
		List<Org> data = orgDao.getOrgByBlurOrgName(orgName);
		if(data.size() == 0) {
			throw new DemoException(ErrorEnum.ORG_NOT_FOUND);
		}else {
			return data;
		}
	}
}
