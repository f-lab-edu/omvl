package com.omvl.omvl.repository;

import com.omvl.omvl.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

	private static final Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;

	@Override
	//회원을 저장하는 메소드
	public Member save(Member member) {
		member.setId(++sequence);

		store.put(sequence, member);

		return member;
	}

	@Override
	//id로 회원 찾는 메소드
	public Member findById(Long id) {
		return store.get(id);
	}

	@Override
	//memberId로 회원 찾는 메소드
	public Member findByMemberId(String memberId) {
		List<Member> members = findAll();

		for (Member member : members) {
			if(member.getMemberId().equals(memberId)){
				return member;
			}
		}

		return null;
	}

	@Override
	//회원 전체 조회를 위한 메소드
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

	//테스트를 위한 store 초기화 메소드
	public void clear() {
		store.clear();
	}
}