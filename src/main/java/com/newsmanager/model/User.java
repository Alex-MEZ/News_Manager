package com.newsmanager.model;

import lombok.Data;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false, unique = true)
    private String username;

    @Column(length = 80, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20)
    private String surname;

    @Column(length = 20)
    private String parentName;

    private LocalDateTime creationDate;
    private LocalDateTime lastEditDate;

    @Enumerated(EnumType.STRING)
    private Role role;
}

