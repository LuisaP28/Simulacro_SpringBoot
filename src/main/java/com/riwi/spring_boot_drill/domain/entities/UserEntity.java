package com.riwi.spring_boot_drill.domain.entities;

import java.util.ArrayList;
import java.util.List;

import com.riwi.spring_boot_drill.utils.enums.RoleUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100)
    private String fullName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleUser role;

    @OneToMany(
        mappedBy = "userId",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    @Builder.Default
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(
        mappedBy = "instructorId",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    @Builder.Default
    private List<Course> courses = new ArrayList<>();

    @OneToMany(
        mappedBy = "userId",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    @Builder.Default
    private List<Submission> submissions = new ArrayList<>();

    @OneToMany(
        mappedBy = "senderId",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    @Builder.Default
    private List<Message> messagesSender = new ArrayList<>();

    @OneToMany(
        mappedBy = "receiverId",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    @Builder.Default
    private List<Message> messagesReceiver = new ArrayList<>();
}
