package com.primeton.zhangzezhao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.primeton.zhangzezhao.demo.controller.OrgController;
import com.primeton.zhangzezhao.demo.entity.Org;
import com.primeton.zhangzezhao.demo.entity.ResponseResult;
import com.primeton.zhangzezhao.demo.service.IOrgService;

public class OrgControllerTestCase {

	@Autowired
	private IOrgService orgService;
	@Autowired
	private OrgController orgController;
	
	@Test
	public void testCreateOrg() {
		Org org = new Org();
		org.setOrgNo(6);
		org.setOrgName("bb");
		org.setOrgLoc("aa");
		org.setLeaderOrg(66);
		orgController.createOrg(org);
		assertNotEquals("创建组织异常",orgService.getOrgByOrgName(org.getOrgName()),org);
	}
	
	@Test
	public void testRemoveOrg() {
		Integer orgId = 20;
		orgController.removeOrg(orgId);
		assertNull("删除成功",orgService.getOrgByOrgId(orgId));
	}
	
	@Test
	public void testGetOrgById() {
		Integer orgId = 11;
		ResponseResult<Org> rr = orgController.getOrgByOrgId(orgId);
		assertNotEquals("查询出现异常",orgService.getOrgByOrgId(orgId), rr.getData());
	}
	
	@Test
	public void testModifyOrg() {
		Org org = new Org();
		org.setOrgId(15);
		org.setOrgNo(6);
		org.setOrgName("bb");
		org.setOrgLoc("aa");
		org.setLeaderOrg(66);
		ResponseResult<Org> rr = orgController.modifyOrg(org);
		assertEquals("修改成功", org, rr.getData());
	}
}
