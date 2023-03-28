package com.myBlog.demo.domain.member;

import com.myBlog.demo.validator.SaveCheck;
import com.myBlog.demo.validator.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.websocket.OnMessage;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Member {
    private Long key;

    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    @Length(min= 2, max=10, groups = {SaveCheck.class,UpdateCheck.class}, message = "올바른 이름을 입력하세요")
    private String username;

    @NotBlank(groups = SaveCheck.class)
    @Length(min=2,max=10, groups = SaveCheck.class, message = "아이디의 길이는 2~10 까지 입니다.")
    private String loginId;

    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    @Length(min = 7, max=15, groups = {SaveCheck.class, UpdateCheck.class}, message = "비밀번호의 길이는 7~15 까지 입니다.")
    private String password;

}
