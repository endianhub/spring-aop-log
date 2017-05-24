package com.model;

import java.util.Date;

/**
 * 
 * @author Dingdong
 * @date 2017年5月3日
 */
public class SysLog {

	private Integer id;
	private Integer userId;
	private String content;
	private String operation;
	private Date dateTime;
	private String abnormity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getAbnormity() {
		return abnormity;
	}

	public void setAbnormity(String abnormity) {
		this.abnormity = abnormity;
	}

	@Override
	public String toString() {
		return "SysLog [id=" + id + ", userId=" + userId + ", content=" + content + ", operation=" + operation + ", dateTime=" + dateTime + ", abnormity=" + abnormity + "]";
	}

}
