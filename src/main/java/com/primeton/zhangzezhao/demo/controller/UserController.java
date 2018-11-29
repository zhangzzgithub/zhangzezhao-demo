package com.primeton.zhangzezhao.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.primeton.zhangzezhao.demo.entity.ResponseResult;
import com.primeton.zhangzezhao.demo.entity.User;
import com.primeton.zhangzezhao.demo.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * 用户管理的api
 */
@RestController("userController")
@Api(value = "用户管理的api", tags = "用户管理接口")
public class UserController {

	@Autowired
	private IUserService userService;

	/**
	 * 用户注册
	 * @param user 要注册的用户对象
	 * @return ResponseResult响应实体
	 */
	@RequestMapping(value = "/api/users", method = RequestMethod.POST)
	@ApiOperation(value = "添加用户", response = ResponseResult.class)
	public ResponseResult<User> createUser(@RequestBody @ApiParam("User对象")User user) {
		ResponseResult<User> rr;
		// 调用业务层方法实现注册功能
		User data = userService.createUser(user);
		// 封装返回结果
		rr = new ResponseResult<User>(ResponseResult.STATE_OK, "注册成功！");
		rr.setData(data);
		return rr;
	}

	/**
	 * 用户登录
	 * @param userName 用户名
	 * @param password 密码
	 * @param session session对象
	 * @return  ResponseResult响应实体
	 */
	@RequestMapping(value = "/api/users/actions/login", method = RequestMethod.POST)
	@ApiOperation(value = "用户登录", response = ResponseResult.class)
	public ResponseResult<Void> login(@RequestBody User user,
			@ApiParam("session对象") HttpSession session) {
		// 声明返回值
		ResponseResult<Void> rr;
		// 调用业务层对象执行登录
		User data = userService.login(user.getUserName(), user.getPassword());
		// 将用户信息封装到session中
		session.setAttribute("uid", data.getUserId());
		session.setAttribute("username", data.getUserName());
		// 登录成功
		rr = new ResponseResult<Void>(ResponseResult.STATE_OK, "登录成功！");
		// 执行返回
		return rr;
	} 

	/**
	 * 查询所有用户
	 * @return 所有用户的集合
	 */
	@RequestMapping(value = "/api/users/all", method = RequestMethod.GET)
	@ApiOperation(value = "查询所有用户")
	public List<User> queryAllUsers() {
		List<User> userList = userService.queryAllUsers();
		return userList;
	}


	/**
	 * 根据id查询用户信息
	 * @param id 用户id
	 * @return user对象
	 */
	@RequestMapping(value="/api/users/{id}",method=RequestMethod.GET)
	@ApiOperation(value="根据id查询用户信息",response=User.class)
	public ResponseResult<User> getUserById(@PathVariable @ApiParam("用户id") Integer id) {
		ResponseResult<User> rr;
		User data = userService.getUserByUserId(id);
		rr = new ResponseResult<User>(ResponseResult.STATE_OK,"查询成功");
		rr.setData(data);
		return rr;
	}
	
	/**
	 * 删除用户
	 * @param id 要删除的用户的用户id
	 * @return 响应实体
	 */
	@RequestMapping(value = "/api/users/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除用户", response = ResponseResult.class)
	public ResponseResult<Void> removeUser(@PathVariable @ApiParam("用户id") Integer id) {
		ResponseResult<Void> rr;
		userService.removeUser(id);
		rr = new ResponseResult<Void>(ResponseResult.STATE_OK, "删除成功");
		return rr;
	}

	/**
	 * 修改用户信息
	 * @param user user对象
	 * @return 响应实体
	 */
	@RequestMapping(value = "/api/users", method = RequestMethod.PUT)
	@ApiOperation(value = "修改用户信息", response = ResponseResult.class)
	public ResponseResult<User> modifyUser(@RequestBody User user) {
		ResponseResult<User> rr;
		userService.modifyUser(user);
		// 创建成功时的ResoponseResult
		rr = new ResponseResult<User>(ResponseResult.STATE_OK, "修改成功");
		rr.setData(user);
		// 执行返回
		return rr;
	}
	
	/**
	 * 根据部门号查询该部门的员工
	 * @param orgNo 部门号
	 * @return 
	 */
	@RequestMapping(value = "api/users/query/{orgNo}",method=RequestMethod.GET)
	@ApiOperation(value = "查询某个部门用户信息", response = ResponseResult.class)
	public ResponseResult<List<User>> queryUserByOrgNo(
			@PathVariable @ApiParam("部门号") Integer orgNo){
		ResponseResult<List<User>> rr;
		List<User> users = userService.queryUserByOrgNo(orgNo);
		rr = new ResponseResult<List<User>>(ResponseResult.STATE_OK,"查询成功");
		rr.setData(users);
		return rr;
	}
	
	/**
	 * 根据用户名模糊查询用户
	 * @param userName 用户名
	 * @return 
	 */
	@RequestMapping(value = "api/users/query",method=RequestMethod.GET)
	@ApiOperation(value = "模糊查询用户信息", response = ResponseResult.class)
	public ResponseResult<List<User>> getUserByUserName(
			@RequestParam("userName") @ApiParam("用户名") String userName){
		ResponseResult<List<User>> rr;
		List<User> users = userService.getUserByBlurUserName(userName); 
		rr = new ResponseResult<List<User>>(ResponseResult.STATE_OK,"查询成功");
		rr.setData(users);
		return rr;
	}
}
