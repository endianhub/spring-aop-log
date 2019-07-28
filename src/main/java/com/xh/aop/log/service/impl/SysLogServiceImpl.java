package com.xh.aop.log.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xh.aop.log.dao.ISysLogDao;
import com.xh.aop.log.model.SysLog;
import com.xh.aop.log.service.ISysLogService;

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
