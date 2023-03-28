package com.myBlog.demo.web;

import com.myBlog.demo.domain.member.Member;
import com.myBlog.demo.domain.member.MemberRepository;
import com.myBlog.demo.domain.member.memory.MemoryRepository;
import com.myBlog.demo.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String home(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){
        if(loginMember == null) return "home";

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

}
