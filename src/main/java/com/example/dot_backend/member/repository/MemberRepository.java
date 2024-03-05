package com.example.dot_backend.member.repository;

import com.example.dot_backend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberByEmail(String email);
}