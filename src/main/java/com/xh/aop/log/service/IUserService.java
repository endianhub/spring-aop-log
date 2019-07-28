package com.xh.aop.log.service;

import java.util.List;
import java.util.Map;

import com.xh.aop.log.model.User;

/**
 * 
 * @author Dingdong
 * @date 2017年5月3日
 */
public interface IUserService {

	int insertUser(User user);

	int delete(Integer paramId);

	int update(User user);

	User findById(Integer paramId);

	String selectJson();

	List<User> selectAll();

	List<Map<String, String>> selectMap();

}
