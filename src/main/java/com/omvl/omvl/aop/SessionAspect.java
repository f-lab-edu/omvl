package com.omvl.omvl.aop;

import com.omvl.omvl.domain.Member;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;


@Aspect
@Component
public class SessionAspect {

	private final HttpSession session;

	public SessionAspect(HttpSession session) {
		this.session = session;
	}

	@Before("execution(* com.omvl.omvl.controller.MemberController.items(..)) && args(model, ..)")
	public void setMemberAttributeFromSession(Model model) {
		Member sessionMember = (Member) session.getAttribute("member");
		if (sessionMember != null) {
			model.addAttribute("member", sessionMember);
		}
	}
}