package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.ISysLogDao;
import com.model.SysLog;
import com.service.ISysLogService;

/**
 * 
 * @author Dingdong
 * @date 2017年5月3日
 */
@Service
public class SysLogServiceImpl implements ISysLogService {

	@Resource
	private ISysLogDao dao;

	public void insert(SysLog log) {

		dao.insert(log);
	}

}
