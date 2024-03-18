package com.omvl.omvl.repository;

import com.omvl.omvl.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

	private static Map<String, Member> store = new HashMap<>();

	@Override
	public Member save(Member member) {
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(String id) {
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
			.filter(member -> member.getName().equals(name))
			.findAny();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

}