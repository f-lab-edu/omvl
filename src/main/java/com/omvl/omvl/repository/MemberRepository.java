package com.omvl.omvl.repository;

import com.omvl.omvl.domain.Member;

import com.omvl.omvl.domain.MemberItem;
import java.util.List;

public interface MemberRepository {

	Member save(Member member);

	Member findById(Long id);

	Member findByMemberId(String memberId);

	Member update(String memberId, Member updateParam);

	List<Member> findAll();

	boolean addItem(MemberItem memberItem);

	List<MemberItem> findItem(String memberId);

}
