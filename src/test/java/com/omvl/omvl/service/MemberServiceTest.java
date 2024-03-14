package com.omvl.omvl.service;

import com.omvl.omvl.domain.Member;
import com.omvl.omvl.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

	@Test
	void login() {

		MemoryMemberRepository repository = new MemoryMemberRepository();

		//given
		Member member1 = new Member();

		member1.setId("seok5182");
		member1.setPassword("1234");
		member1.setName("홍석균");
		member1.setGender(1);
		member1.setAge(30);

		repository.save(member1);

		System.out.println("member1 = " + member1);

		Member member = null;

		//when
		if (repository.findById("seok5182").isPresent()) {
			member = repository.findById("seok5182").get();

			if (!member.getPassword().equals("123")) {
				member = null;
			}
		}

		//then
		System.out.println("member = " + member);
	}
}