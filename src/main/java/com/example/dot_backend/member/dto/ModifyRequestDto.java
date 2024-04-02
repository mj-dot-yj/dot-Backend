package com.example.dot_backend.member.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class ModifyRequestDto {
    private String name;
    private String email;
    private String password;
    private String phone;

    public ModifyRequestDto(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public void encodePassword(String encondingPassword) { this.password = encondingPassword; }

}
