package com.omvl.omvl.controller;

import com.omvl.omvl.domain.Member;
import com.omvl.omvl.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

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

    @GetMapping("/members/login")
    public String loginForm() {
        return "members/loginForm";
    }

}
