package com.primeton.zhangzezhao.demo.service;

import java.util.List;

import com.primeton.zhangzezhao.demo.entity.User;
import com.primeton.zhangzezhao.demo.exception.DemoException;

public interface IUserService {

	/**
	 * 添加用户
	 * @param user  新增的用户对象
	 * @return 	新增的用户对象
	 */
	public User createUser(User user) throws DemoException;
	 
	 /**
	 * 根据用户名查询用户信息
	 * @param username 	用户名
	 * @return	查询到的用户对象	查询不到返回null
	 */
	public User getUserByUserName(String userName);
	
	/**
	 * 根据用户id查询用户信息
	 * @param id	用户id
	 * @return	该用户id的用户信息，没有匹配的数据返回null
	 */
	public User getUserByUserId(Integer userId);
	
	/**
	 * 查询所有用户
	 * @return 包含所有用户的集合
	 */
	public List<User> queryAllUsers();
	
	/**
	 * 登录
	 * @param username	用户名
	 * @param password	密码
	 * @return	登录成功的用户数据
	 * @throws DemoException	没有该用户的异常
	 */
	public User login(String userName,String password) throws DemoException;
	
	/**
	 * 根据用户名删除用户信息
	 * @param id 用户名
	 * @return 受影响的行数
	 * @throws DemoException 没有该用户的异常
	 */
	public Integer removeUser(Integer id) throws DemoException;
	
	/**
	 * 修改用户信息
	 * @param uid  用户id
	 * @param username 新用户名
	 * @param password 新密码
	 * @param deptno 新部门编号
	 * @return 受影响的行数
	 * @throws DemoException 没有该用户信息的异常
	 */
	public Integer modifyUser(User user) throws DemoException;
	
	/**
	 * 根据部门号查询该部门所有的用户
	 * @param orgNo 部门号
	 * @return 	所有用户的集合
	 */
	public List<User> queryUserByOrgNo(Integer orgNo);
	
	/**
	 * 根据用户名模糊查询用户信息
	 * @param username 	用户名
	 * @return	查询到的用户对象	查询不到返回null
	 */
	public List<User> getUserByBlurUserName(String userName);
}
