package com.omvl.omvl.repository;

import com.omvl.omvl.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Member member = new Member();

        member.setId("seok5182");
        member.setPassword("1234");
        member.setName("홍석균");
        member.setGender(1);
        member.setAge(30);

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();

        member1.setId("seok5182");
        member1.setPassword("1234");
        member1.setName("홍석균");
        member1.setGender(1);
        member1.setAge(30);

        repository.save(member1);

        Member member2 = new Member();

        member2.setId("hong1352");
        member2.setPassword("0000");
        member2.setName("홍당무");
        member2.setGender(1);
        member2.setAge(25);

        repository.save(member2);

        //when
        Member result = repository.findByName("홍당무").get();

        //then
        assertThat(result).isEqualTo(member2);

    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();

        member1.setId("seok5182");
        member1.setPassword("1234");
        member1.setName("홍석균");
        member1.setGender(1);
        member1.setAge(30);

        repository.save(member1);

        Member member2 = new Member();

        member2.setId("hong1352");
        member2.setPassword("0000");
        member2.setName("홍당무");
        member2.setGender(1);
        member2.setAge(25);

        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }

}