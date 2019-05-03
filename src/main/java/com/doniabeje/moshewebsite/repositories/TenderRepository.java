package com.doniabeje.moshewebsite.repositories;

import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.domains.Tender;
import com.doniabeje.moshewebsite.domains.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface TenderRepository extends PagingAndSortingRepository<Tender, Long> {
    Iterable<Tender> findByTitleContains(String title);
    Page<Tender> findAllByDeadlineBefore(Date date, Pageable pageable);
    Page<Tender> findAllByDeadlineAfter(Date date, Pageable pageable);
    Page<Tender> findAllByDeadlineBeforeAndLanguage(Date date, News.Language language, Pageable pageable);
    Page<Tender> findAllByDeadlineAfterAndLanguage(Date date, News.Language language, Pageable pageable);
}
