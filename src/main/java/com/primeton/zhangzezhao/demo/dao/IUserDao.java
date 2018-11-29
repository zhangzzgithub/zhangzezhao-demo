package com.primeton.zhangzezhao.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.primeton.zhangzezhao.demo.entity.User;

@Mapper
public interface IUserDao {

	/**
	 * 添加用户
	 * @param user  新增的用户对象
	 * @return 	受影响的行数
	 */
	@Insert(
			"INSERT INTO zhangzz_demo_user("
			+ "user_name,password,org_no)"
			+ "VALUES (#{userName},#{password},#{orgNo})")
	@Options(useGeneratedKeys=true,keyProperty="userId",keyColumn="user_id")
	Integer insertUser(User user);
	
	/**
	 * 根据用户名查询用户信息
	 * @param username	用户名
	 * @return 该用户名的用户信息，没有匹配的数据返回null
	 */
	@Select("SELECT user_id AS userId,user_name AS userName,password,org_no AS orgNo "
			+ "FROM zhangzz_demo_user "
			+ "WHERE user_name=#{userName}")
	User getUserByUserName(String userName);
	
	/**
	 * 查询所有用户
	 * @return 包含所有用户的集合
	 */
	@Select("SELECT "
			+ "u.user_id AS userId,u.user_name AS userName,u.password,u.org_no AS orgNo,"
			+ "o.org_name AS orgName "
			+ "FROM zhangzz_demo_user u JOIN zhangzz_demo_org o ON u.org_no=o.org_no")
	List<User> queryAllUsers();
	
	/**
	 * 根据用户id查询用户信息
	 * @param id	用户id
	 * @return	该用户id的用户信息，没有匹配的数据返回null
	 */
	@Select("SELECT user_id AS userId,user_name AS userName,password,org_no AS orgNo "
			+ "FROM zhangzz_demo_user "
			+ "WHERE user_id=#{userId}")
	User getUserById(Integer userId);
	
	/**
	 * 根据用户id删除用户信息
	 * @param userId 用户id
	 * @return 受影响的行数
	 */
	@Delete("DELETE FROM zhangzz_demo_user WHERE user_id=#{userId}")
	Integer deleteUser(Integer id);
	
	/**
	 * 修改用户信息
	 * @param user	封装了被修改的用户的id(必选)、新用户名、新密码、新部门编号
	 * @return		受影响的行数
	 */
	@Update("UPDATE zhangzz_demo_user "
			+ "SET user_name=#{userName},password=#{password},org_no=#{orgNo} "
			+ "WHERE user_id=#{userId}")
	Integer updateUser(User user);
	
	/**
	 * 根据部门号查询下属用户
	 * @param orgNo 部门号
	 * @return 所有用户的集合
	 */
	@Select("SELECT u.user_name AS userName,u.password,u.org_no AS orgNo,o.org_name AS orgName "
			+ "FROM zhangzz_demo_user u JOIN zhangzz_demo_org o ON u.org_no=o.org_no WHERE u.org_no=#{orgNo}")
	public List<User> queryUserByOrgNo(Integer orgNo);
	
	/**
	 * 根据用户名模糊查询用户信息
	 * @param username	用户名
	 * @return 该用户名的用户信息，没有匹配的数据返回null
	 */
	@Select("SELECT u.user_id AS userId,u.user_name AS userName,u.password,"
			+ "u.org_no AS orgNo,o.org_name AS orgName "
			+ "FROM zhangzz_demo_user u JOIN zhangzz_demo_org o ON u.org_no=o.org_no "
			+ "WHERE u.user_name LIKE #{userName}")
	List<User> getUserByBlurUserName(String userName);
	
}
