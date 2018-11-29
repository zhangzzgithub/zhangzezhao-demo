package com.primeton.zhangzezhao.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeton.zhangzezhao.demo.dao.IUserDao;
import com.primeton.zhangzezhao.demo.entity.User;
import com.primeton.zhangzezhao.demo.exception.DemoException;
import com.primeton.zhangzezhao.demo.exception.ErrorEnum;
import com.primeton.zhangzezhao.demo.util.Validator;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	@Transactional(rollbackFor=DemoException.class)
	public User createUser(User user) {
		if (!Validator.checkUsername(user.getUserName())) {
			throw new DemoException(ErrorEnum.ILLEGAL_USERNAME);
		}
		if (!Validator.checkPassword(user.getPassword())) {
			throw new DemoException(ErrorEnum.ILLEGAL_PASSWORD);
		}
		User data = getUserByUserName(user.getUserName());
		if (data == null) {
			userDao.insertUser(user);
			return user;
		} else {
			throw new DemoException(ErrorEnum.USERNAME_CONFLICT);
		}
	}

	public User login(String userName, String password) {
		// 调用自身的方法查询用户名匹配的数据
		User user = getUserByUserName(userName);
		// 判断是否查询到数据
		if (user != null) {
			// 存在
			// 如果密码匹配，返回该User对象
			if (password.equals(user.getPassword())) {
				user.setPassword(password);
				return user;
			} else {
				// 密码不匹配
				throw new DemoException(ErrorEnum.ERROR_PASSWORD);
			}
		} else {
			// 没有找到与该用户名匹配的数据
			throw new DemoException(ErrorEnum.USER_NOT_FOUND);
		}
	}

	@Override
	public List<User> queryAllUsers() {
		return userDao.queryAllUsers();
	}

	@Transactional(rollbackFor=DemoException.class)
	@Override
	public Integer removeUser(Integer id) {
		// 调用自身的方法查询用户名匹配的数据
		User data = getUserByUserId(id);
		// 找到数据执行删除
		if (data != null) {
			Integer result = userDao.deleteUser(id);
			return result;
		} else {
			throw new DemoException(ErrorEnum.USER_NOT_FOUND);
		}
	}

	@Override
	@Transactional(rollbackFor=DemoException.class)
	public Integer modifyUser(User user) {
		Integer rows = userDao.updateUser(user);
		if (rows == 1) {
			return 1;
		} else {
			throw new DemoException(ErrorEnum.USER_NOT_FOUND);
		}
	}
	
	@Override
	public User getUserByUserId(Integer userId) {
		User data = userDao.getUserById(userId);
		if(data == null) {
			throw new DemoException(ErrorEnum.USER_NOT_FOUND);
		}else {
			return data;
		}
	}
	
	@Override
	public User getUserByUserName(String userName) {
		User data = userDao.getUserByUserName(userName);
		return data;
	}

	@Override
	public List<User> queryUserByOrgNo(Integer orgNo) {
		return userDao.queryUserByOrgNo(orgNo);
	}

	@Override
	public List<User> getUserByBlurUserName(String userName) {
		userName = "%"+userName+"%";
		List<User> userList = userDao.getUserByBlurUserName(userName);
		if(userList.size() == 0) {
			throw new DemoException(ErrorEnum.USER_NOT_FOUND);
		}else {
			return userList;
		}
	}
}
