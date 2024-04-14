package com.omvl.omvl.repository;

import com.omvl.omvl.domain.Member;

import java.util.List;

public interface MemberRepository {

	Member save(Member member);

	Member findById(Long id);

	Member findByMemberId(String memberId);

	Member update(String memberId, Member updateParam);

	List<Member> findAll();

}
