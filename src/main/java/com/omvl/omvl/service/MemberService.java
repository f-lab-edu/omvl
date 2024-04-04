package com.omvl.omvl.service;

import com.omvl.omvl.domain.Member;
import com.omvl.omvl.repository.MemberRepository;

public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/**
	 * 회원가입
	 */
	public Member join(Member member) {

		return memberRepository.save(member);
	}

	/**
	 * memberId 중복검사
	 */
	public boolean validateDuplicateMember(String memberId) {

		Member member = memberRepository.findByMemberId(memberId);

		return member == null;

	}

	/**
	 * 로그인
	 * 1. 고객이 입력한 memberId가 있는지 findByMemberId()로 확인
	 * 2-1. 없다면 null 반환
	 * 2-2. 있다면 고객이 입력한 memberPassword와 해당하는 계정의 memberPassword 일치여부 확인
	 * 3-1. memberPassword가 일치하지 않는다면 null 반환
	 * 3-2. memberPassword가 일치하면 해당 member 반환
	 */
	public Member login(String memberId, String memberPassword) {

		Member member = memberRepository.findByMemberId(memberId);

		if (member==null) {
			return null;
		} else if (!member.getMemberPassword().equals(memberPassword)) {
			return null;
		}

		return member;
	}
}
