package com.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dao.IUserDao;
import com.model.User;
import com.service.IUserService;

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

		return dao.selectAll();
	}

	public List<Map<String, String>> selectMap() {

		return dao.selectMap();
	}

	public int insertUser(User user) {
		
//		user = null;
		
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
