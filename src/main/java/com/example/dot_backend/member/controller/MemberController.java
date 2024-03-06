package com.example.dot_backend.member.controller;

import com.example.dot_backend.common.ApiResponse;
import com.example.dot_backend.jwt.JwtToken;
import com.example.dot_backend.member.dto.LoginRequestDto;
import com.example.dot_backend.member.dto.SignupRequestDto;
import com.example.dot_backend.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse<Long>> signUp(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        try {
            Long memberId = memberService.signUp(signupRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(memberId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.onFailure("failed to register"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtToken>> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            JwtToken jwtToken = memberService.login(loginRequestDto);
            memberService.updateLoginDate(loginRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.onFailure("failed to login"));
        }
    }
}
