package com.omvl.omvl.repository;

import com.omvl.omvl.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        repository.clear();
    }

    @Test
    public void save() {
        //given
        Member member = new Member();

        member.setMemberId("abc");
        member.setMemberPassword("1234");
        member.setType(0);

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId());
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findById() {
        //given
        Member member1 = new Member();

        member1.setMemberId("abc");
        member1.setMemberPassword("1234");
        member1.setType(0);

        repository.save(member1);

        Member member2 = new Member();

        member2.setMemberId("abcd");
        member2.setMemberPassword("1234");
        member2.setType(1);

        repository.save(member2);

        //when
        Member result = repository.findById(member1.getId());

        //then
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();

        member1.setMemberId("abc");
        member1.setMemberPassword("1234");
        member1.setType(0);

        repository.save(member1);

        Member member2 = new Member();

        member2.setMemberId("abcd");
        member2.setMemberPassword("1234");
        member2.setType(1);

        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }

}