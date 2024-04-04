package com.omvl.omvl;

import com.omvl.omvl.repository.MemberRepository;
import com.omvl.omvl.repository.MemoryMemberRepository;
import com.omvl.omvl.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 컴포넌트 스캔 방식 사용 안하는 이유
 * -> MemoryMemberRepository를 다른 repository로 변경할 예정이기 때문입니다
 * -> 컴포넌트 스캔 방식을 사용하게 되면 기존 + 새로 생성된 repository에 어노테이션 수정이 필요합니다.
 * -> 수동 등록 방식을 사용하면 수정을 한번만 하면 됩니다.
 */
@Configuration
public class SpringConfig {

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
