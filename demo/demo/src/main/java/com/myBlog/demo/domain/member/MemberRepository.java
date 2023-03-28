package com.myBlog.demo.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Member findByKey(Long key);

    Optional<Member> findByLoginId(String loginId);
    List<Member> findAll();
}
