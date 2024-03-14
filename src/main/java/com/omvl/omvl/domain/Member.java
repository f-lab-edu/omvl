package com.omvl.omvl.domain;

import java.io.Serializable;

/**
 * Serializable 사용한 이유
 * 일반적으로 메모리에 생성된 객체는 '비영속성'을 가지는데
 * 세션에 저장되어야 할 Member 객체 같은 경우 '영속성' 을 가져야 한다.
 * 영속성을 가지기 위해 자바에서 제공하는 직렬화 기능을 사용해야 하는데
 * 그 기능을 사용하기 위해서는 해당 클래스에 Serializable을 사용하면 된다.
 */
public class Member implements Serializable {

	private String id;
	private String password;
	private String name;
	private int gender;
	private int age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
