package com.omvl.omvl.controller;

import com.omvl.omvl.domain.Member;
import com.omvl.omvl.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  //회원가입 페이지로 이동
  @GetMapping("/members/new")
  public String createForm() {
    return "members/createMemberForm";
  }

  //회원가입 진행
  @PostMapping("/members/new")
  public String create(MemberForm form) {
    Member member = new Member();

    member.setId(form.getId());
    member.setPassword(form.getPassword());
    member.setName(form.getName());
    member.setAge(form.getAge());
    member.setGender(form.getGender());

    memberService.join(member);

    return "redirect:/";
  }

  //로그인 페이지로 이동
  @GetMapping("/members/login")
  public String loginForm() {
    return "members/loginForm";
  }

  //로그인 진행
  @PostMapping("/members/login")
  public String login(LoginForm form, HttpServletRequest request) {
    //세션 생성
    HttpSession session = request.getSession();
    session.setMaxInactiveInterval(3600);

    Member member = memberService.login(form.getId(), form.getPassword());

    if (member != null) {
      //세션 저장
      session.setAttribute("member", member);

      return "redirect:/welcome";
    } else {
      return "redirect:/";
    }

  }

}
