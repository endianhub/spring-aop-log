package com.xh.aop.log.common.validators;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2019年7月28日
 */
public class ValidateCheck {

	/**
	 * <b>Title: 校验参数是否正确</b>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * 
	 * @param value
	 * @param express
	 * @param errMsg
	 * @param errClass
	 * @return
	 */
	public static ErrorMsg checkByExpress(String value, String express, String errMsg, ErrorMsg errClass) {

		return errClass;
	}

}
