package com.primeton.zhangzezhao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.primeton.zhangzezhao.demo.ZhangzezhaoDemoApplication;
import com.primeton.zhangzezhao.demo.controller.UserController;
import com.primeton.zhangzezhao.demo.entity.ResponseResult;
import com.primeton.zhangzezhao.demo.entity.User;
import com.primeton.zhangzezhao.demo.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ZhangzezhaoDemoApplication.class)
@WebAppConfiguration
public class UserControllerTestCase {

	@Autowired
	private IUserService userService;
	@Autowired
	private UserController userController;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setUserName("zzzzzz");
		user.setPassword("123456");
		user.setOrgNo(33);
		userController.createUser(user);
		assertNotEquals("用户注册异常",userService.getUserByUserName(user.getUserName()),user);
	}
	
	@Test
	public void testRemoveUser() {
		Integer id = 4;
		userController.removeUser(id);
		assertNull("删除成功",userService.getUserByUserId(id));
	}
	
	@Test
	public void testGetUserById() {
		Integer id = 11;
		ResponseResult<User> rr = userController.getUserById(id);
		assertNotEquals("查询用户异常",userService.getUserByUserId(id), rr.getData());
	}
	
	@Test
	public void testModifyUser() {
		User user = new User();
		user.setUserId(6);
		user.setUserName("ggggg");
		user.setPassword("123456");
		user.setOrgNo(33);
		ResponseResult<User> rr = userController.modifyUser(user);
		assertEquals("修改成功", user, rr.getData());
	}
}
