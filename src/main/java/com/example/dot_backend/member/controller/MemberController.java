package com.example.dot_backend.member.controller;

import com.example.dot_backend.common.ApiResponse;
import com.example.dot_backend.config.CustomUserDetails;
import com.example.dot_backend.member.dto.*;
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
    public ResponseEntity<ApiResponse<TokenResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto) {
        TokenResponseDto tokenResponseDto = memberService.login(loginRequestDto);
        memberService.updateLoginDate(loginRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(tokenResponseDto));
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<ApiResponse<String>> checkPassword(@RequestBody PasswordRequestDto passwordRequestDto,
                                                             @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        boolean isMatched = memberService.checkPassword(passwordRequestDto, customUserDetails);
        if (isMatched){
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess("Success"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.onFailure("Passwords do not match"));
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<ApiResponse<String>> deleteMember(@PathVariable Long idx) {
        memberService.deleteMember(idx);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess("Success"));
    }

    @PostMapping("/info/{idx}")
    public ResponseEntity<ApiResponse<MemberDto>> findMemberByEmail(@PathVariable Long idx) {
        MemberDto member = memberService.findMemberByEmail(idx);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(member));
    }

    @PostMapping("/modify/{idx}")
    public ResponseEntity<ApiResponse<Long>> modifyMember(@RequestBody ModifyRequestDto modifyRequestDto, @PathVariable Long idx) {
        Long memberId = memberService.modifyMember(modifyRequestDto, idx);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(memberId));
    }
}
