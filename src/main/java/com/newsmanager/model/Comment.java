package com.newsmanager.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300, nullable = false)
    private String text;

    private LocalDateTime creationDate;
    private LocalDateTime lastEditDate;

    private Long insertedById;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News news;
}
