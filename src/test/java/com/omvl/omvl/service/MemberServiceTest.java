package com.omvl.omvl.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.omvl.omvl.domain.Member;
import com.omvl.omvl.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();
	MemberService service = new MemberService(repository);

	@AfterEach
	void afterEach() {
		repository.clear();
	}

	@Test
	public void join_fail_idDuplicate() {
		//given
		Member member1 = new Member("abc", "1234", 1);
		Member member2 = new Member("abc", "123", 2);

		//when
		Member joinedMember1 = service.join(member1);
		Member joinedMember2 = service.join(member2);

		//then
		assertThat(joinedMember1).isEqualTo(member1);
		assertThat(joinedMember2).isEqualTo(null);
	}

	@Test
	public void join_ok() {
		//given
		Member member1 = new Member("abc", "1234", 1);

		//when
		Member joinedMember1 = service.join(member1);

		//then
		assertThat(joinedMember1).isEqualTo(member1);
	}

	@Test
	public void isDuplicate_true() {
		//given
		Member member1 = new Member("abc", "1234", 1);
		Member member2 = new Member("abc", "12345", 2);

		Member joinedMember1 = service.join(member1);

		//when
		boolean isDuplicate = service.duplicated(member2.getMemberId());

		//then
		assertThat(isDuplicate).isEqualTo(true);
	}

	@Test
	public void isDuplicate_false() {
		//given
		Member member1 = new Member("abc", "1234", 1);
		Member member2 = new Member("abcd", "12345", 2);

		Member joinedMember1 = service.join(member1);

		//when
		boolean isDuplicate = service.duplicated(member2.getMemberId());

		//then
		assertThat(isDuplicate).isEqualTo(false);
	}

	@Test
	public void login_ok() {
		//given
		Member member1 = service.join(new Member("abc", "1234", 1));

		//when
		Member loginMember = service.login("abc", "1234");

		//then
		assertThat(loginMember).isEqualTo(member1);
	}

	@Test
	public void login_fail() {
		//given
		Member member1 = service.join(new Member("abc", "1234", 1));

		//when
		Member loginMember = service.login("abc", "123");

		//then
		assertThat(loginMember).isEqualTo(null);
	}

}