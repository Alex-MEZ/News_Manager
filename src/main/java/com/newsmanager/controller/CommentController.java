package com.newsmanager.controller;

import com.newsmanager.model.Comment;
import com.newsmanager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<Page<Comment>> getAllComments(
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return ResponseEntity.ok(commentService.getAll(pageable));
    }

    @PreAuthorize("hasRole('SUBSCRIBER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @RequestParam Long userId) {
        return ResponseEntity.ok(commentService.create(comment, userId));
    }

    @PreAuthorize("hasRole('SUBSCRIBER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment, @RequestParam Long userId) {
        return ResponseEntity.ok(commentService.update(id, comment, userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}