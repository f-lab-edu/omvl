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

	//회원 인덱스용 id
	private Long id;
	//회원이 사용할 id
	private String memberId;
	//회원이 사용할 password
	private String memberPassword;
	//회원이 선택한 유형
	private int type;

	public Member() {
	}

	public Member(String memberId, String memberPassword, int type) {
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
