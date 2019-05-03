package com.doniabeje.moshewebsite.services;

import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

public interface NewsService {
    public News save(News news);

    public Iterable<News> saveAll(Iterable<News> news);

    Optional<News> findById(Long id);

    boolean existsById(Long id);

    Iterable<News> findAll();
    Iterable<News> findByTitleContains(String title);
    Page<News> findAllByLanguage(News.Language language, PageRequest pageRequest);

    Page<News> findAllByLanguageAndDateTimeBefore(News.Language language, Date date, Pageable pageable);
    Page<News> findAll(Pageable pageRequest);

    Iterable<News> findAllById(Iterable<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(News news);

    void deleteAll(Iterable<News> news);

    void deleteAll();
}

@Service
class NewsServiceImpl implements NewsService{
    @Autowired
    NewsRepository newsRepository;

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public Iterable<News> saveAll(Iterable<News> news) {
        return newsRepository.saveAll(news);
    }

    @Override
    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return newsRepository.existsById(id);
    }

    @Override
    public Iterable<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public Iterable<News> findByTitleContains(String title) {
        return newsRepository.findByTitleContains(title);
    }

    @Override
    public Page<News> findAllByLanguage(News.Language language, PageRequest pageRequest) {
        return newsRepository.findAllByLanguage(language, pageRequest);
    }

    @Override
    public Page<News> findAllByLanguageAndDateTimeBefore(News.Language language, Date date, Pageable pageable) {
        return newsRepository.findAllByLanguageAndDateTimeBefore(language, date, pageable);
    }

    @Override
    public Page<News> findAll(Pageable pageRequest) {
        return newsRepository.findAll(pageRequest);
    }

    @Override
    public Iterable<News> findAllById(Iterable<Long> ids) {
        return newsRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return newsRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public void delete(News news) {
        newsRepository.delete(news);
    }

    @Override
    public void deleteAll(Iterable<News> news) {
        newsRepository.deleteAll(news);
    }

    @Override
    public void deleteAll() {
        newsRepository.deleteAll();
    }
}