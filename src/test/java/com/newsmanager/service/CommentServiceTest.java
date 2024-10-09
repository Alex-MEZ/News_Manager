package com.newsmanager.service;

import com.newsmanager.model.Comment;
import com.newsmanager.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateComment() {
        Comment comment = new Comment();
        comment.setText("Test Comment");
        comment.setCreationDate(LocalDateTime.now());

        when(commentRepository.save(comment)).thenReturn(comment);

        Comment createdComment = commentService.create(comment, 1L);

        assertEquals("Test Comment", createdComment.getText());
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void testGetAllComments() {
        Page<Comment> page = new PageImpl<>(Collections.singletonList(new Comment()));
        when(commentRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Comment> result = commentService.getAll(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        verify(commentRepository, times(1)).findAll(any(PageRequest.class));
    }
}
