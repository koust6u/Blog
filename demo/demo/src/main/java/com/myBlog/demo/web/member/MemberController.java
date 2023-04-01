package com.myBlog.demo.web.member;


import com.myBlog.demo.domain.login.LoginService;
import com.myBlog.demo.domain.member.Member;
import com.myBlog.demo.domain.member.MemberRepository;
import com.myBlog.demo.domain.member.MemberUpdateDto;
import com.myBlog.demo.domain.member.memory.MemoryRepository;
import com.myBlog.demo.validator.SaveCheck;
import com.myBlog.demo.web.SessionConst;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;
    private final LoginService loginService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member){
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated(SaveCheck.class) @ModelAttribute Member member, BindingResult result){
        if(!loginService.duplicateId(member.getLoginId())){
            result.reject("signup fail","중복된 id 입니다.");
            return "/members/addMemberForm";
        }
        if(result.hasErrors()) return "members/addMemberForm";
        log.info("username ={}", member.getName());
        memberRepository.save(member);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(@SessionAttribute(name= SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                          @ModelAttribute("update") MemberUpdateDto param){
        if(loginMember == null) return "redirect:/login";
        param.setLoginId(loginMember.getLoginId());
        param.setName(loginMember.getName());
        param.setPassword(loginMember.getPassword());
        return "/profile/profileChange";
    }

    @PostMapping("/profile")
    public String update(@ModelAttribute("update") MemberUpdateDto param,
                         @SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                         BindingResult result){
        if(result.hasErrors()) return "redirect:/members/profile";
        Long keyId = loginMember.getId();
        log.info("login Id: {}", param.getLoginId());
        memberRepository.update(keyId, param);
        return "redirect:/";
    }

}
