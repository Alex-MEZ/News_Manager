package com.newsmanager.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String text;

    private LocalDateTime creationDate;
    private LocalDateTime lastEditDate;

    private Long insertedById;
    private Long updatedById;
}
