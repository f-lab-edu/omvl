package com.omvl.omvl.repository;

import com.omvl.omvl.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

  Member save(Member member);

  Optional<Member> findById(String id);

  Member login(String id, String password);

  Optional<Member> findByName(String name);

  List<Member> findAll();

}
