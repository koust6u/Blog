package com.myBlog.demo.web.service;

import com.myBlog.demo.domain.member.Member;
import com.myBlog.demo.domain.member.MemberRepository;
import com.myBlog.demo.domain.member.MemberUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface MemberService {
    Member save(Member member);
    void update(Long id, MemberUpdateDto updateParam);

    Optional<Member> findByKey(Long key);
    Optional<Member> findByLoginId(String loginId);

}