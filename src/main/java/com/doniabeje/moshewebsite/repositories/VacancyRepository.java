package com.doniabeje.moshewebsite.repositories;

import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.domains.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface VacancyRepository extends PagingAndSortingRepository<Vacancy, Long> {
    Iterable<Vacancy> findByTitleContains(String title);
    Page<Vacancy> findAllByDeadlineBefore(Date date, Pageable pageable);
    Page<Vacancy> findAllByDeadlineAfter(Date date, Pageable pageable);
    Page<Vacancy> findAllByDeadlineBeforeAndLanguage(Date date, News.Language language, Pageable pageable);
    Page<Vacancy> findAllByDeadlineAfterAndLanguage(Date date, News.Language language, Pageable pageable);
}
