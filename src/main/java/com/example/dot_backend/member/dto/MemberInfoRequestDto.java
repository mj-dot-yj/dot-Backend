package com.example.dot_backend.member.dto;

import com.example.dot_backend.member.entity.Member;
import com.example.dot_backend.member.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Data
public class MemberInfoRequestDto {
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "Check your email")
    private String email;
    @Pattern(regexp = "^[A-Za-z0-9]{6,12}$", message = "Check your password")
    private String password;
    @NotBlank (message = "Check your name")
    private String name;
    @Pattern (regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "Check your phone number")
    private String phone;

    public void encodePassword(String encodingPassword){
        this.password = encodingPassword;
    }

    public Member toSaveMember() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .phone(this.phone)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .role(Role.ROLE_USER)
                .build();
    }

    @Builder
    public MemberInfoRequestDto(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
}