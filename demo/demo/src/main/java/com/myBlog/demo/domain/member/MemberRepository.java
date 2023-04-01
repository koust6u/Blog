package com.myBlog.demo.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByKey(Long key);

    void update(Long id,MemberUpdateDto updateParam);
    Optional<Member> findByLoginId(String loginId);
}
