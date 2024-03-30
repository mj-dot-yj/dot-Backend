package com.example.dot_backend.member.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class MemberDto {
    private String name;
    private String email;
    private String phone;
}
