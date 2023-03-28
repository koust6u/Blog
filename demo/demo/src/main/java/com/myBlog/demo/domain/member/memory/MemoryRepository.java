package com.myBlog.demo.domain.member.memory;

import com.myBlog.demo.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;


@Slf4j
@Repository
public class MemoryRepository {
    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용
    public Member save(Member member) {
        member.setKey(++sequence);
        log.info("save: member={}", member);
        store.put(member.getKey(), member);
        return member;
    }
    public Member findByKey(Long id) {
        return store.get(id);
    }
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
