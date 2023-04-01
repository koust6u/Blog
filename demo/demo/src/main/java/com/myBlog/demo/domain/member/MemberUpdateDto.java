package com.myBlog.demo.domain.member;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MemberUpdateDto {


    @NotBlank
    @Length(min = 2, max = 10, message = "올바른 이름을 입력해주세요")
    private String name;

    @NotBlank
    @Length(min=2, max = 10, message = "아이디의 길이는 2~ 10 까지 입니다.")
    private String loginId;

    @NotBlank
    @Length(min =7, max=15, message = "비밀번호의 길이는 7~15 까지 입니다.")
    private String password;
}
