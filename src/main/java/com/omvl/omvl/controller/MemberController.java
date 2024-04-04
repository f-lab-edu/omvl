package com.omvl.omvl.controller;

import com.omvl.omvl.domain.Member;
import com.omvl.omvl.service.MemberService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	//회원가입 페이지로 이동
	@GetMapping("/new")
	public String createForm() {
		return "members/createMemberForm";
	}

	//회원가입 진행
	@PostMapping("/new")
	public String create(@ModelAttribute Member member) {

		memberService.join(member);

		return "redirect:/";
	}

	//로그인 페이지로 이동
	@GetMapping("/login")
	public String loginForm() {
		return "members/loginForm";
	}

	//로그인 진행
	@PostMapping("/login")
	public String login(@RequestParam("memberId") String memberId,
						@RequestParam("memberPassword") String memberPassword,
						HttpServletRequest request) {
		//세션 생성
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(3600);

		Member member = memberService.login(memberId, memberPassword);

		if (member != null) {
			//세션 저장
			session.setAttribute("member", member);

			return "redirect:/items";
		} else {
			return "redirect:/";
		}

	}

	/**
	 * 테스트를 위한 PostConstruct
	 */
	@PostConstruct
	public void init() {
		memberService.join(new Member("abc", "1234", 0));
		memberService.join(new Member("abcd", "123", 1));
	}

}
