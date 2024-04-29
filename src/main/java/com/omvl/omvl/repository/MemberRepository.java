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

	boolean removeItem(String memberId, String itemName);

	List<MemberItem> findItem(String memberId);

}
