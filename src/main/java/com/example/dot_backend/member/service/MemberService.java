package com.example.dot_backend.member.service;

import com.example.dot_backend.config.CustomUserDetails;
import com.example.dot_backend.jwt.JwtToken;
import com.example.dot_backend.jwt.JwtTokenProvider;
import com.example.dot_backend.member.dto.*;
import com.example.dot_backend.member.entity.Member;
import com.example.dot_backend.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public Long signUp(SignupRequestDto signupRequestDto) {
        boolean isDuplicated = memberRepository.findMemberByEmail(signupRequestDto.getEmail()).isPresent();
        if (isDuplicated) {
            throw new RuntimeException("duplicated email");
        }

        signupRequestDto.encodePassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        Member savedMember = memberRepository.save(signupRequestDto.toSaveMember());
        return savedMember.getId();
    }

    @Transactional
    public TokenResponseDto login(LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JwtToken jwtToken = jwtTokenProvider.generateTokenDto(authentication);
        Member member = memberRepository.findMemberByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new RuntimeException("no user"));

        return new TokenResponseDto(member.getId(), jwtToken);
    }

    @Transactional
    public void updateLoginDate(LoginRequestDto loginRequestDto){
        Member updateMember = memberRepository.findMemberByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new RuntimeException("no user"));
        updateMember.updateLoginDate();
    }

    public boolean checkPassword(PasswordRequestDto passwordRequestDto, CustomUserDetails customUserDetails){
        return passwordEncoder.matches(passwordRequestDto.getPassword(), customUserDetails.getPassword());
    }

    public void deleteMember(Long idx){
        Member deleteMember = memberRepository.findById(idx).orElseThrow(() -> new RuntimeException("no user"));
        memberRepository.delete(deleteMember);
    }

    public MemberDto findMemberByEmail(Long idx) {
        Member findMember = memberRepository.findById(idx).orElseThrow(() -> new RuntimeException("no user"));
        return MemberDto.builder()
                .name(findMember.getName())
                .email(findMember.getEmail())
                .phone(findMember.getPhone())
                .build();
    }

    @Transactional
    public Long modifyMember(ModifyRequestDto modifyRequestDto, Long idx) {
        Member updateMember = memberRepository.findById(idx).orElseThrow(() -> new RuntimeException("no user"));
        modifyRequestDto.encodePassword(passwordEncoder.encode(modifyRequestDto.getPassword()));
        updateMember.updateMember(modifyRequestDto.getEmail(), modifyRequestDto.getPassword(), modifyRequestDto.getName(), modifyRequestDto.getPhone());

        return updateMember.getId();
    }
}
