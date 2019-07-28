package com.xh.aop.log.common.aop;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.xh.aop.log.common.annotation.OperationLogger;
import com.xh.aop.log.common.tool.ThreadPoolUtil;
import com.xh.aop.log.model.SysLog;
import com.xh.aop.log.service.ISysLogService;

/**
 * AOP切点类
 * 
 * @author Dingdong
 * @date 2017年5月23日
 */
public class SystemLogAspect {

	private static final Logger LOGGER = LogManager.getLogger(SystemLogAspect.class);
	private static final String LOG_CONTENT = "[类名]:%s,[方法]:%s,[参数]:%s";
	private static final String[] METHOD_CONTENT = { "insert", "delete", "update", "save" };

	private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");

	@Resource
	private ISysLogService logService;

	/**
	 * 前置通知
	 * 
	 * @author Dingdong
	 * @date 2017年5月22日
	 * 
	 * @param joinPoint
	 */
	public void before(JoinPoint joinPoint) {

		// LOGGER.info("前置通知");
		// saveLog(joinPoint, null);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String methodName = joinPoint.getSignature().getName();
		String message = operateContent(joinPoint, methodName, request);

		LOGGER.info(" 前置通知 ： " + message);

		// 前置通知 (在方法执行之前返回)用于拦截Controller层记录用户的操作的开始时间
		// 线程绑定变量（该数据只有当前请求的线程可见）
		Date beginTime = new Date();
		beginTimeThreadLocal.set(beginTime);

		saveLog(joinPoint, null);
	}

	/**
	 * <b>Title: </b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param joinPoint
	 */
	private void after(JoinPoint joinPoint) {
		// LOGGER.info("后置通知");
		// saveLog(joinPoint, null);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String methodName = joinPoint.getSignature().getName();
		String message = this.operateContent(joinPoint, methodName, request);

		LOGGER.info(" 后置通知 ： " + message);

		// saveLog(joinPoint, null);
	}

	/**
	 * 异常操作并带有返回值
	 * 
	 * @author Dingdong
	 * @date 2017年5月24日
	 * 
	 * @param joinPoint
	 * @param argObj
	 */
	public void afterThrowing(JoinPoint joinPoint, Exception exception) {
		// LOGGER.info("异常操作");
		// saveLog(joinPoint, exception);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String methodName = joinPoint.getSignature().getName();
		String message = this.operateContent(joinPoint, methodName, request);

		LOGGER.info(" 异常操作 : " + message + " ，   exception ： " + exception);
	}

	/**
	 * <b>Title: </b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param point
	 * @param returnValue
	 */
	private void afterReturning(JoinPoint joinPoint, Object returnValue) {
		// LOGGER.info("后置返回");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String methodName = joinPoint.getSignature().getName();
		String message = this.operateContent(joinPoint, methodName, request);

		LOGGER.info(" 后置返回 : " + message + "，  返回的结果集为： " + returnValue);

		// saveLog(joinPoint, null);
	}

	/**
	 * <b>Title: </b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param point
	 * @return
	 */
	private Object around(ProceedingJoinPoint point) {
		// LOGGER.info("日志环绕");
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String className = point.getTarget().getClass().getName();
			String methodName = point.getSignature().getName();
			Method method = this.currentMethod(point, methodName);

			String message = this.operateContent(point, methodName, request);
			// 打印日志
			Object obj = point.proceed();
			LOGGER.info(" ==== 日志环绕 ：  ===== " + message + "， 日志： " + obj);

			// saveLog(point, null);
			// 保存日志
			return obj;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 保存日志到数据库
	 * 
	 * @author Dingdong
	 * @date 2017年5月24日
	 * 
	 * @param joinPoint
	 * @param exception
	 */
	private void saveLog(JoinPoint joinPoint, Exception exception) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String methodName = joinPoint.getSignature().getName();// 方法名
		Method method = currentMethod(joinPoint, methodName);
		OperationLogger log = method.getAnnotation(OperationLogger.class);

		SysLog logs = new SysLog();
		logs.setUserId(1);
		logs.setDateTime(new Date());
		logs.setContent(operateContent(joinPoint, methodName, request));
		if (exception != null) {
			LOGGER.info("Service出现异常");
			logs.setOperation("异常");
			logs.setAbnormity(exception.toString());
			logService.insert(logs);
		} else {
			LOGGER.info("方法名：" + methodName);
			// if (isWriteLog(methodName)) {
			logs.setOperation((log != null) ? log.description() : null);

			// 调用线程保存至数据库
			ThreadPoolUtil.getPool().execute(new SaveSystemLogThread(logs, logService));
			// logService.insert(logs);
			// }
		}
	}

	/**
	 * <b>Title: 保存日志</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * @email xhaimail@163.com
	 * @date 2019年7月28日
	 */
	private static class SaveSystemLogThread implements Runnable {
		private SysLog logs;
		private ISysLogService logService;

		public SaveSystemLogThread(SysLog logs, ISysLogService logService) {
			this.logs = logs;
			this.logService = logService;
		}

		@Override
		public void run() {
			LOGGER.info(" logs : " + Thread.currentThread().getName() + " === " + JSON.toJSONString(logs));
			logService.insert(logs);
		}
	}

	/**
	 * 判断是哪些方法可以写入LOG
	 * 
	 * @author Dingdong
	 * @date 2017年5月24日
	 * 
	 * @param method
	 * @return
	 */
	private boolean isWriteLog(String method) {
		boolean falg = false;
		for (String s : METHOD_CONTENT) {
			if (method.indexOf(s) > -1) {
				falg = true;
				break;
			}
		}
		return falg;
	}

	/**
	 * 获取当前执行的方法并判断
	 *
	 * @param joinPoint
	 *            连接点
	 * @param methodName
	 *            方法名称
	 * @return 方法
	 */
	private Method currentMethod(JoinPoint joinPoint, String methodName) {
		Method[] methods = joinPoint.getTarget().getClass().getMethods();
		Method resultMethod = null;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				resultMethod = method;
				break;
			}
		}
		return resultMethod;
	}

	/**
	 * 获取当前传递的参数
	 *
	 * @param joinPoint
	 *            连接点
	 * @param methodName
	 *            方法名称
	 * @return 操作内容
	 */
	private String operateContent(JoinPoint joinPoint, String methodName, HttpServletRequest request) {
		String className = joinPoint.getTarget().getClass().getName();
		Object[] params = joinPoint.getArgs();
		StringBuffer bf = new StringBuffer();
		if (params != null && params.length > 0) {
			Enumeration<String> paraNames = request.getParameterNames();
			while (paraNames.hasMoreElements()) {
				String key = paraNames.nextElement();
				bf.append(key).append("=");
				bf.append(request.getParameter(key)).append("&");
			}
			if (StringUtils.isBlank(bf.toString())) {
				bf.append(request.getQueryString());
			}
		}
		return String.format(LOG_CONTENT, className, methodName, bf.toString());
	}
}
