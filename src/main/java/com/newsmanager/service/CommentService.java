package com.newsmanager.service;

import com.newsmanager.model.Comment;
import com.newsmanager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment create(Comment comment, Long userId) {
        comment.setCreationDate(LocalDateTime.now());
        comment.setInsertedById(userId);
        return commentRepository.save(comment);
    }

    public Comment update(Long id, Comment comment, Long userId) {
        Comment existingComment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        existingComment.setText(comment.getText());
        existingComment.setLastEditDate(LocalDateTime.now());
        return commentRepository.save(existingComment);
    }

    @Cacheable("comments")
    public Page<Comment> getAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
