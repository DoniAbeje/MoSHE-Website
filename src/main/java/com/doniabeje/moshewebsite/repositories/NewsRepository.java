package com.doniabeje.moshewebsite.repositories;

import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.domains.NewsImage;
import com.doniabeje.moshewebsite.domains.Tender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface NewsRepository extends PagingAndSortingRepository<News, Long> {
    Iterable<News> findByTitleContains(String title);
    Page<News> findAllByLanguage(News.Language language, Pageable pageable);
    Page<News> findAllByLanguageAndDateTimeBefore(News.Language language, Date date, Pageable pageable);
}
