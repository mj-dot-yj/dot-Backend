package com.example.dot_backend.challenge.entity;


import com.example.dot_backend.challenge.dto.ChallengeResponseDto;
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
    private String period;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public ChallengeResponseDto getChallengeResponseDto() {
        return ChallengeResponseDto.builder()
                .id(this.id)
                .userId(this.userId)
                .title(this.title)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .alarmed(this.alarmed)
                .count(this.count)
                .totalCount(this.totalCount)
                .period(this.period)
                .build();
    }
}
