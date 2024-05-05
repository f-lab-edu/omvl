package com.omvl.omvl.service;

import com.omvl.omvl.domain.Member;
import com.omvl.omvl.domain.MemberItem;
import com.omvl.omvl.repository.MemberRepository;
import java.util.List;

public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/**
	 * 회원가입
	 */
	public Member join(Member member) {

		boolean isDuplicated = duplicated(member.getMemberId());

		if (isDuplicated) {
			return null;
		}

		return memberRepository.save(member);
	}

	/**
	 * memberId 중복검사
	 */
	public boolean duplicated(String memberId) {

		Member member = memberRepository.findByMemberId(memberId);

		return member != null;

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

	/**
	 * 회원수정
	 */
	public Member edit(String memberId, Member updateParam) {

		return memberRepository.update(memberId, updateParam);
	}

	/**
	 * 장바구니 담기
	 */
	public boolean addItem(MemberItem memberItem) {

		return memberRepository.addItem(memberItem);
	}

	/**
	 * 장바구니에서 제거
	 */
	public boolean removeItem(String memberId, String itemName) {

		return memberRepository.removeItem(memberId, itemName);
	}

	/**
	 * 장바구니 조회
	 */
	public List<MemberItem> findItem(String memberId) {

		return memberRepository.findItem(memberId);
	}

}
