package com.example.dot_backend.member.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class PasswordRequestDto {
    private String password;

    @JsonCreator
    public PasswordRequestDto(String password) {
        this.password = password;
    }
}