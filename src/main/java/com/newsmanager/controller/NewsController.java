package com.newsmanager.controller;

import com.newsmanager.model.News;
import com.newsmanager.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<Page<News>> getAllNews(Pageable pageable) {
        return ResponseEntity.ok(newsService.getAll(pageable));
    }

    @PreAuthorize("hasRole('JOURNALIST') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News news, @RequestParam Long userId) {
        return ResponseEntity.ok(newsService.create(news, userId));
    }

    @PreAuthorize("hasRole('JOURNALIST') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News news, @RequestParam Long userId) {
        return ResponseEntity.ok(newsService.update(id, news, userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
