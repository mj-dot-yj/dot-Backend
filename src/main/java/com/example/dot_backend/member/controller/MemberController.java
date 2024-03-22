package com.example.dot_backend.member.controller;

import com.example.dot_backend.common.ApiResponse;
import com.example.dot_backend.config.CustomUserDetails;
import com.example.dot_backend.jwt.JwtToken;
import com.example.dot_backend.member.dto.LoginRequestDto;
import com.example.dot_backend.member.dto.PasswordRequestDto;
import com.example.dot_backend.member.dto.SignupRequestDto;
import com.example.dot_backend.member.entity.Member;
import com.example.dot_backend.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
        Long memberId = memberService.signUp(signupRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(memberId));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtToken>> login(@RequestBody LoginRequestDto loginRequestDto) {
        JwtToken jwtToken = memberService.login(loginRequestDto);
        memberService.updateLoginDate(loginRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(jwtToken));
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<ApiResponse<String>> checkPassword(@RequestBody PasswordRequestDto passwordRequestDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        boolean isMatched = memberService.checkPassword(passwordRequestDto, customUserDetails);
        if (isMatched){
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess("Success"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.onFailure("Passwords do not match"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> deleteMember(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        memberService.deleteMember(customUserDetails.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess("Success"));
    }

    @GetMapping("/info/{email}")
    public ResponseEntity<Member> findMemberByEmail(@PathVariable String email) {
        Member member = memberService.findMemberByEmail(email);
        return ResponseEntity.ok(member);
    }
}
