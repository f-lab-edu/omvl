package com.omvl.omvl.repository;

import com.omvl.omvl.domain.Member;

import com.omvl.omvl.domain.MemberItem;
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
	public Member update(String memberId, Member updateParam) {
		Member findMember = findByMemberId(memberId);
		findMember.setMemberPassword(updateParam.getMemberPassword());
		findMember.setType(updateParam.getType());

		return findMember;
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

	@Override
	public boolean addItem(MemberItem memberItem) {
		return false;
	}

	@Override
	public boolean removeItem(String memberId, String itemName) {
		return false;
	}

	@Override
	public List<MemberItem> findItem(String memberId) {
		return null;
	}

	//테스트를 위한 store 초기화 메소드
	public void clear() {
		store.clear();
	}
}