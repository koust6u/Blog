package com.myBlog.demo.config;


import com.myBlog.demo.domain.member.MemberRepository;
import com.myBlog.demo.domain.member.jdbcrepository.JdbcTemplateRepository;
import com.myBlog.demo.web.service.MemberService;
import com.myBlog.demo.web.service.MemberServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateConfig {
    private final DataSource dataSource;
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JdbcTemplateRepository(dataSource);
    }
}
