package com.omvl.omvl.service;

import com.omvl.omvl.domain.Member;
import com.omvl.omvl.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberService {

	private final MemberRepository memberRepository;

	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/**
	 * 회원가입
	 */
	public String join(Member member) {

		validateDuplicateMember(member); //중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {

		memberRepository.findById(member.getId())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});

	}

	/**
	 * 로그인
	 */
	public Member login(String id, String password) {
		Member member = null;

		if (memberRepository.findById(id).isPresent()) {
			String memberPassword = memberRepository.findById(id).get().getPassword();
			if (memberPassword.equals(password)) {
				return memberRepository.findById(id).get();
			}
		}

		return member;
	}
}
