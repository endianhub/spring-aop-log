package com.xh.aop.log.common.validators;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * <b>Title: 参数验证</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2019年7月28日
 */
public class Validate {

	/**
	 * <b>Title: 校验抛出异常</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param errorMsg
	 * @throws Exception
	 */
	public void throwException(ErrorMsg errorMsg) throws Exception {
		if (!errorMsg.getErrorCode().equals("0000")) {
			throw new RuntimeException(errorMsg.getErrorMsg());
		}
	}


	/**
	 * <b>Title: 校验入参</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param point
	 * @return
	 */
	public Object validate(ProceedingJoinPoint point) {
		return point;
	}

}
