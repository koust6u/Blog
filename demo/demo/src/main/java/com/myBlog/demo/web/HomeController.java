package com.myBlog.demo.web;

import com.myBlog.demo.domain.member.Member;
import com.myBlog.demo.domain.member.MemberRepository;
import com.myBlog.demo.domain.member.memory.MemoryRepository;
import com.myBlog.demo.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;


@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    public final MemberRepository memberRepository;
    @GetMapping("/")
    public String home(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){
        if(loginMember == null) return "home";
        Member member = memberRepository.findByKey(loginMember.getId()).orElse(null);
        if(member == null) return "home";
        loginMember.setName(member.getName());

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/about")
    public String about(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember){
      return"/about/about";
    }

}
