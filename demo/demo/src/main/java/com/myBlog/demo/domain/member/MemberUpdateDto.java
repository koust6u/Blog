package com.myBlog.demo.domain.member;


import lombok.Data;

@Data
public class MemberUpdateDto {
    private String name;
    private String loginId;
    private String password;
}
