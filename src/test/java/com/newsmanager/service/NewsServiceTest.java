package com.newsmanager.service;

import com.newsmanager.model.News;
import com.newsmanager.repository.NewsRepository;
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

class NewsServiceTest {

    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private NewsService newsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNews() {
        News news = new News();
        news.setTitle("Test News");
        news.setText("Test Content");
        news.setCreationDate(LocalDateTime.now());

        when(newsRepository.save(news)).thenReturn(news);

        News createdNews = newsService.create(news, 1L);

        assertEquals("Test News", createdNews.getTitle());
        verify(newsRepository, times(1)).save(news);
    }

    @Test
    void testGetAllNews() {
        Page<News> page = new PageImpl<>(Collections.singletonList(new News()));
        when(newsRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<News> result = newsService.getAll(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        verify(newsRepository, times(1)).findAll(any(PageRequest.class));
    }
}
