package com.newsmanager.service;

import com.newsmanager.model.News;
import com.newsmanager.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public News create(News news, Long userId) {
        news.setCreationDate(LocalDateTime.now());
        news.setInsertedById(userId);
        return newsRepository.save(news);
    }

    public News update(Long id, News news, Long userId) {
        News existingNews = newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
        existingNews.setTitle(news.getTitle());
        existingNews.setText(news.getText());
        existingNews.setLastEditDate(LocalDateTime.now());
        existingNews.setUpdatedById(userId);
        return newsRepository.save(existingNews);
    }

    @Cacheable("news")
    public Page<News> getAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    public void delete(Long id) {
        newsRepository.deleteById(id);
    }
}