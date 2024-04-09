package com.omvl.omvl.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.omvl.omvl.domain.Member;
import com.omvl.omvl.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();
	MemberService service = new MemberService(repository);

	@Test
	public void 회원가입_이미가입된아이디가있으면회원가입안됨() {
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
	public void 회원가입_회원가입() {
		//given
		Member member1 = new Member("abc", "1234", 1);

		//when
		Member joinedMember1 = service.join(member1);

		//then
		assertThat(joinedMember1).isEqualTo(member1);
	}

	@Test
	public void 아이디중복조회_중복된경우true리턴() {
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
	public void 아이디중복조회_중복이아닌경우false리턴() {
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
	public void 로그인_성공하면member리턴() {
		//given
		Member member1 = service.join(new Member("abc", "1234", 1));

		//when
		Member loginMember = service.login("abc", "1234");

		//then
		assertThat(loginMember).isEqualTo(member1);
	}

	@Test
	public void 로그인_실패하면null리턴() {
		//given
		Member member1 = service.join(new Member("abc", "1234", 1));

		//when
		Member loginMember = service.login("abc", "123");

		//then
		assertThat(loginMember).isEqualTo(null);
	}

}