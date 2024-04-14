package com.omvl.omvl.controller;

import com.omvl.omvl.domain.Member;
import com.omvl.omvl.domain.MemberItem;
import com.omvl.omvl.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

		Member joinedMember = memberService.join(member);

		if (joinedMember == null) {
			return "redirect:/new";
		}

		return "redirect:/";
	}

	//아이디 중복 체크
	@PostMapping("/checkId")
	public ResponseEntity<Map<String, Boolean>> checkIdDuplication(@RequestParam("memberId") String memberId) {

		boolean isDuplicated = memberService.duplicated(memberId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("exists", isDuplicated);

		return ResponseEntity.ok(response);
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
			return "redirect:/members/login";
		}

	}

	//회원수정 페이지로 이동
	@GetMapping("/{memberId}/edit")
	public String editForm(@PathVariable("memberId") String memberId) {
		return "/members/editForm";
	}

	//회원수정 진행
	@PostMapping("/{memberId}/edit")
	public String edit(@PathVariable("memberId") String memberId, @ModelAttribute Member member, HttpSession session) {
		Member editMember = memberService.edit(memberId, member);
		session.removeAttribute("member");
		session.setAttribute("member", editMember);
		return "redirect:/items";
	}

	//장바구니 담기
	@PostMapping("/{memberId}/addItem")
	public ResponseEntity<Map<String, Boolean>> addItems(@PathVariable("memberId") String memberId,
						   								 @ModelAttribute MemberItem memberItem) {
		boolean isAddItemsOk = memberService.addItem(memberItem);
		Map<String, Boolean> response = new HashMap<>();
		response.put("result", isAddItemsOk);

		return ResponseEntity.ok(response);
	}

	//장바구니 조회
	@GetMapping("/{memberId}/items")
	public String items(@PathVariable("memberId") String memberId, Model model) {
		List<MemberItem> memberItems = memberService.findItem(memberId);
		model.addAttribute("memberItems", memberItems);
		return "members/items";
	}

	/**
	 * 테스트를 위한 PostConstruct
	 */
//	@PostConstruct
//	public void init() {
//		memberService.join(new Member("abc", "1234", 1));
//		memberService.join(new Member("abcd", "123", 2));
//	}

}
