package com.xh.aop.log.model;

/**
 * 
 * @author Dingdong
 * @date 2017年5月3日
 */
public class User {

	private Integer id;
	private String name;
	private String password;
	private Integer age;
	private Integer sex;

	public User() {
		super();
	}

	public User(String name, String password, Integer age, Integer sex) {
		super();
		this.name = name;
		this.password = password;
		this.age = age;
		this.sex = sex;
	}

	public User(Integer id, String name, String password, Integer age, Integer sex) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.sex = sex;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", age=" + age + ", sex=" + sex + "]";
	}

	public String toStringValue() {
		return "[" + id + ", " + name + ", " + password + ", " + age + ", " + sex + "]";
	}

}
