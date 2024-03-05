package com.example.dot_backend.member.dto;

import com.example.dot_backend.member.entity.Member;
import com.example.dot_backend.member.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Data
public class SignupRequestDto {
    private String email;
    private String password;
    private String name;
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
                .createdDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .role(Role.ROLE_USER)
                .build();
    }

    @Builder
    public SignupRequestDto(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
}