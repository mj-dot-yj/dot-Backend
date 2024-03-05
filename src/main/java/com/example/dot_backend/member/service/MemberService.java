package com.example.dot_backend.member.service;

import com.example.dot_backend.member.dto.SignupRequestDto;
import com.example.dot_backend.member.entity.Member;
import com.example.dot_backend.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
//    private PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long signUp(SignupRequestDto signupRequestDto) {
        Member member = memberRepository.findMemberByEmail(signupRequestDto.getEmail());
        if (member != null) {
            throw new RuntimeException("duplicated email");
        }
//        signupRequestDto.encodePassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        Member savedMember = memberRepository.save(signupRequestDto.toSaveMember());
        return savedMember.getId();
    }
}