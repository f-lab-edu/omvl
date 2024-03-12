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
    public Member login(String id, String password) {
        if(store.get(id) != null){
            Member member = store.get(id);
            if(member.getPassword().equals(password)){
                return store.get(id);
            } else {
                return null;
            }
        } else {
            return null;
        }
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