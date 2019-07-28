package com.test;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Dingdong
 * @date 2017年5月24日
 */
public class TestMain {

	private static final String[] METHOD = { "insert", "delete", "update", "find", "query", "sum", "add", "edit", "login", "logout", "add", "edit", "delete", "grant" };

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		System.out.println(isMethod("qadds"));
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

		startTime = System.currentTimeMillis();
		System.out.println(isWriteLog("qadsds"));
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}

	private static boolean isMethod(String methodName) {
		boolean falg = false;
		int i = 0;
		for (String s : METHOD) {
			if (StringUtils.startsWith(methodName, METHOD[i])) {
				falg = true;
				break;
			}
			i++;
		}
		return falg;
	}

	private static boolean isWriteLog(String method) {
		boolean falg = false;
		for (String s : METHOD) {
			if (method.indexOf(s) > -1) {
				falg = true;
				break;
			}
		}
		return falg;
	}
}
