package com.xh.aop.log.dao;

import java.util.List;
import java.util.Map;

import com.xh.aop.log.model.User;

/**
 * 
 * @author Dingdong
 * @date 2017年5月3日
 */
public interface IUserDao {

	int insert(User user);

	int delete(Integer paramId);

	int update(User user);

	User findById(Integer paramId);

	List<User> selectAll();
	
	List<Map<String, String>> selectMap();

}
