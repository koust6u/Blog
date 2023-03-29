package com.myBlog.demo.web.service;

import com.myBlog.demo.domain.member.Member;
import com.myBlog.demo.domain.member.MemberRepository;
import com.myBlog.demo.domain.member.MemberUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public Member save(Member member) {
        memberRepository.save(member);
        return member;
    }

    @Override
    public void update(Long key, MemberUpdateDto updateParam) {
        memberRepository.update(key,updateParam);
    }

    @Override
    public Optional<Member> findByKey(Long key) {
        return memberRepository.findByKey(key);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}
