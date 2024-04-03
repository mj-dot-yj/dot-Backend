package com.example.dot_backend.member.dto;

import com.example.dot_backend.jwt.JwtToken;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class TokenResponseDto {
    private Long idx;
    private JwtToken jwtToken;

    public TokenResponseDto(Long idx, JwtToken jwtToken) {
        this.idx = idx;
        this.jwtToken = jwtToken;
    }
}
