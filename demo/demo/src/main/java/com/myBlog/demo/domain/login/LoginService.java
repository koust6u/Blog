package com.myBlog.demo.domain.login;

import com.myBlog.demo.domain.member.Member;
import com.myBlog.demo.domain.member.MemberRepository;
import com.myBlog.demo.domain.member.memory.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemoryRepository memberRepository;

    public Member login(String loginId, String password){
        return memberRepository.findByLoginId(loginId)
                .filter(m-> m.getPassword().equals(password))
                        .orElse(null);
    }
}
