package com.doniabeje.moshewebsite.services;

import com.doniabeje.moshewebsite.domains.NewsImage;
import com.doniabeje.moshewebsite.repositories.NewsImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface NewsImageService {
    public NewsImage save(NewsImage newsImage);

    public Iterable<NewsImage> saveAll(Iterable<NewsImage> newsImages);

    Optional<NewsImage> findById(Long id);

    boolean existsById(Long id);

    Iterable<NewsImage> findAll();
    Page<NewsImage> findAll(Pageable pageRequest);

    Iterable<NewsImage> findAllById(Iterable<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(NewsImage newsImage);

    void deleteAll(Iterable<NewsImage> newsImages);

    void deleteAll();
}

@Service
class NewsImageServiceImpl implements NewsImageService{
    @Autowired
    NewsImageRepository newsImageRepository;

    @Override
    public NewsImage save(NewsImage newsImage) {
        return newsImageRepository.save(newsImage);
    }

    @Override
    public Iterable<NewsImage> saveAll(Iterable<NewsImage> newsImages) {
        return newsImageRepository.saveAll(newsImages);
    }

    @Override
    public Optional<NewsImage> findById(Long id) {
        return newsImageRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return newsImageRepository.existsById(id);
    }

    @Override
    public Iterable<NewsImage> findAll() {
        return newsImageRepository.findAll();
    }



    @Override
    public Page<NewsImage> findAll(Pageable pageRequest) {
        return newsImageRepository.findAll(pageRequest);
    }

    @Override
    public Iterable<NewsImage> findAllById(Iterable<Long> ids) {
        return newsImageRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return newsImageRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        newsImageRepository.deleteById(id);
    }

    @Override
    public void delete(NewsImage newsImage) {
        newsImageRepository.delete(newsImage);
    }

    @Override
    public void deleteAll(Iterable<NewsImage> newsImages) {
        newsImageRepository.deleteAll(newsImages);
    }

    @Override
    public void deleteAll() {
        newsImageRepository.deleteAll();
    }
}