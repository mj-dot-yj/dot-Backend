package com.example.dot_backend.challenge.entity;


import com.example.dot_backend.challenge.enums.Period;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Challenge {
    @Id
    @Column(name="challenge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="userId")
    private Long userId;
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long alarmed;
    private Long count;
    private Long totalCount;
    @Enumerated(EnumType.STRING)
    private Period period;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
