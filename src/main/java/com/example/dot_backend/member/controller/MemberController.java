package com.example.dot_backend.member.controller;

import com.example.dot_backend.member.dto.SignupRequestDto;
import com.example.dot_backend.member.service.MemberService;
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
    public ResponseEntity<Long> signUp(@RequestBody SignupRequestDto signupRequestDto) throws Exception {
        try{
            Long memberId = memberService.signUp(signupRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
        } catch (Exception e){
            throw new Exception("failed to register");
        }
    }
}
