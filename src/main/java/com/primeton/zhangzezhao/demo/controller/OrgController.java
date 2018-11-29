package com.primeton.zhangzezhao.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.primeton.zhangzezhao.demo.entity.Org;
import com.primeton.zhangzezhao.demo.entity.ResponseResult;
import com.primeton.zhangzezhao.demo.service.IOrgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * 组织级管理的api
 */
@RestController
@Api(value="组织级管理的api",tags="组织级管理接口")
public class OrgController {
	
	@Autowired
	private IOrgService orgService;
	
	/**
	 * 添加部门
	 * @param org 需要添加的部门对象
	 * @return ResponseResult响应实体
	 */
	@RequestMapping(value="/api/orgs",method=RequestMethod.POST)
	@ApiOperation(value="添加部门",response=ResponseResult.class)
	public ResponseResult<Org> createOrg(@RequestBody Org org){
		ResponseResult<Org> rr;
		Org data = orgService.createOrg(org);
		rr = new ResponseResult<Org>(ResponseResult.STATE_OK,"创建部门成功!");
		rr.setData(data);
		return rr;
	}
	
	/**
	 * 删除部门
	 * @param orgId 部门id
	 * @return ResponseResult响应实体
	 */
	@RequestMapping(value="/api/orgs/{orgId}",method=RequestMethod.DELETE)
	@ApiOperation(value="删除部门",response=ResponseResult.class)
	public ResponseResult<Void> removeOrg(
			@PathVariable("orgId") @ApiParam(value="部门id",required=true) Integer orgId){
		ResponseResult<Void> rr;
		orgService.removeOrg(orgId);
		rr = new ResponseResult<Void>(ResponseResult.STATE_OK,"删除部门成功！");
		return rr;
	}
	
	/**
	 * 查询部门信息
	 * @param orgId 部门id
	 * @return ResponseResult响应实体
	 */
	@RequestMapping(value="/api/orgs/{orgId}",method=RequestMethod.GET)
	@ApiOperation(value="查询部门信息",response=ResponseResult.class)
	public ResponseResult<Org> getOrgByOrgId(
			@PathVariable 
			@ApiParam(value="部门id",required=true) 
			Integer orgId){
		ResponseResult<Org> rr;
		Org data = orgService.getOrgByOrgId(orgId);
		rr = new ResponseResult<Org>(ResponseResult.STATE_OK,"查找成功");
		rr.setData(data);
		return rr;
	}
	
	/**
	 * 查询所有部门
	 * @return 所有组织的集合
	 */
	@RequestMapping(value = "/api/orgs/all", method = RequestMethod.GET)
	@ApiOperation(value = "查询所有部门")
	public List<Org> queryAllOrgs(){
		List<Org> orgList = orgService.queryAllOrgs();
		return orgList;
	}
	
	/**
	 * 修改部门信息 
	 * @param org 部门对象
	 * @return ResponseResult响应实体
	 */
	@RequestMapping(value="/api/orgs",method=RequestMethod.PUT)
	@ApiOperation(value="修改部门信息",response=ResponseResult.class)
	public ResponseResult<Org> modifyOrg(@RequestBody Org org){
		ResponseResult<Org> rr;
		orgService.modifyOrg(org);
		rr = new ResponseResult<Org>(ResponseResult.STATE_OK,"修改成功");
		rr.setData(org);
		return rr;
	}
	
	/**
	 * 查询所有下属部门
	 * @param leaderOrg 上级部门号
	 * @return	部门集合
	 */
	@RequestMapping(value="/api/orgs",method=RequestMethod.GET)
	public List<Org> getOrgsByLeaderOrg(@RequestParam Integer leaderOrg){
		List<Org> orgs = orgService.getOrgByLeaderOrg(leaderOrg);
		return orgs;
	}
	
	/**
	 * 模糊查询组织
	 */
	@RequestMapping(value="/api/orgs/query",method=RequestMethod.GET)
	public ResponseResult<List<Org>> queryOrgByBulrOrgName(@RequestParam("orgName") String orgName){
		ResponseResult<List<Org>> rr;
		List<Org> orgs = orgService.getOrgByBlurOrgName(orgName);
		rr = new ResponseResult<List<Org>>(ResponseResult.STATE_OK,"查询成功");
		rr.setData(orgs);
		return rr;
	}
}
