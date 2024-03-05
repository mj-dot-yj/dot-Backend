package com.example.dot_backend.member.entity;

import com.example.dot_backend.member.enums.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private LocalDate createdDate;
    @Column
    private LocalDate updatedDate;
    @Column
    private LocalDate lastLoginDate;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(Long id, String email, String password, String name, String phone, LocalDate createdDate, LocalDate updatedDate, LocalDate lastLoginDate, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.lastLoginDate = lastLoginDate;
        this.role = role;
    }

}