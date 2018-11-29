package com.primeton.zhangzezhao.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.primeton.zhangzezhao.demo.entity.Org;

@Mapper
public interface IOrgDao {
	
	/**
	 * 根据组织名查询组织信息
	 * @param orgName 组织名称
	 * @return	查询到的组织对象
	 */
	@Select("SELECT org_id AS orgId,org_no AS orgNo,"
			+ "org_name AS orgName,leader_org AS leaderOrg,org_loc AS orgLoc"
			+ " FROM zhangzz_demo_org WHERE org_name=#{orgName}")
	public Org getOrgByOrgName(String orgName);
	
	/**
	 * 根据组织id查询组织信息
	 * @param orgId 组织id
	 * @return
	 */
	@Select("SELECT org_id AS orgId,org_no AS orgNo,org_name AS orgName,leader_org AS leaderOrg,org_loc AS orgLoc"
			+ " FROM zhangzz_demo_org WHERE org_id=#{orgId}")
	public Org getOrgByOrgId(Integer orgId);
	
	/**
	 * 查询所有的组织
	 * @return 所有组织的集合
	 */
	@Select("SELECT "
			+ "a.org_id AS orgId,a.org_no AS orgNo,a.org_name AS orgName,"
			+ "b.org_name AS leaderOrgName,"
			+ "a.org_loc AS orgLoc "
			+ "FROM zhangzz_demo_org a JOIN zhangzz_demo_org b ON a.leader_org=b.org_no" )
	public List<Org> queryAllOrgs();
	
	/**
	 * 添加组织
	 * @param org  组织数据存放的对象
	 * @return   受影响的行数
	 */
	@Insert("INSERT INTO "
			+ "zhangzz_demo_org"
			+ "(org_id,org_no,org_name,org_loc,leader_org) "
			+ "VALUES "
			+ "(#{orgId},#{orgNo}, #{orgName}, #{orgLoc},#{leaderOrg})")
	@Options(useGeneratedKeys=true,keyProperty="orgId",keyColumn="org_id")
	public Integer insertOrg(Org org);
	
	/**
	 * 根据组织编号修改组织信息
	 * @param org	需要修改的组织对象
	 * @return 受影响的行数
	 */
	@Update("UPDATE zhangzz_demo_org "
			+ "SET org_no=#{orgNo},org_name=#{orgName},org_loc=#{orgLoc},leader_org=#{leaderOrg} "
			+ "WHERE org_id=#{orgId}")
	public Integer updateOrg(Org org);
	
	/**
	 * 根据组织id删除组织
	 * @param orgId  组织id
	 * @return 受影响的行数
	 */
	@Delete("DELETE FROM zhangzz_demo_org WHERE org_id=#{orgId}")
	public Integer deleteOrg(Integer orgId);
	
	/**
	 * 根据上级部门查询下属部门
	 * @param leaderOrg
	 * @return
	 */
	@Select("SELECT org_id AS orgId,org_no AS orgNo, org_name AS orgName,"
			+ "leader_org AS leaderOrg,org_loc AS orgLoc "
			+ "FROM zhangzz_demo_org WHERE leader_org=#{leaderOrg}")
	public List<Org> getOrgByLeaderOrg(Integer leaderOrg);
	
	/**
	 * 模糊查询组织
	 * @return 所有组织的集合
	 */
	@Select("SELECT "
			+ "a.org_id AS orgId,a.org_no AS orgNo,a.org_name AS orgName,"
			+ "b.org_name AS leaderOrgName,"
			+ "a.org_loc AS orgLoc "
			+ "FROM zhangzz_demo_org a JOIN zhangzz_demo_org b ON a.leader_org=b.org_no "
			+ "WHERE a.org_name LIKE #{orgName}" )
	public List<Org> getOrgByBlurOrgName(String orgName);
	
}
