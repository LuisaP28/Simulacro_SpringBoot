package com.riwi.spring_boot_drill.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   
    
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity senderId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity receiverId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Course courseId;

    @Column(nullable = false)
    private String content;

    @Builder.Default
    private LocalDateTime date = LocalDateTime.now();   
}
