package com.xh.aop.log.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xh.aop.log.dao.IUserDao;
import com.xh.aop.log.model.User;
import com.xh.aop.log.service.IUserService;

/**
 * 
 * @author Dingdong
 * @date 2017年5月3日
 */
@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao dao;

	public String selectJson() {
		List<User> list = dao.selectAll();
		return JSON.toJSONString(list);
	}

	public List<User> selectAll() {
		// if (true) {
		// throw new RuntimeException("异常");
		// }
		return dao.selectAll();
	}

	public List<Map<String, String>> selectMap() {
		return dao.selectMap();
	}

	public int insertUser(User user) {
		return dao.insert(user);
	}

	public int delete(Integer paramId) {
		return dao.delete(paramId);
	}

	public int update(User user) {
		return dao.update(user);
	}

	public User findById(Integer paramId) {
		return dao.findById(paramId);
	}

}
