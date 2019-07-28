package com.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xh.aop.log.model.SysLog;
import com.xh.aop.log.service.ISysLogService;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2019年7月28日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-config.xml" })
public class LogServiceTest {

	@Resource
	private ISysLogService logService;

	@Test
	public void insert() {
		SysLog log = new SysLog();
		log.setUserId(1);
		log.setContent("测试");
		log.setOperation("测试");
		log.setDateTime(new Date());
		log.setAbnormity("测试");

		logService.insert(log);
	}

}
