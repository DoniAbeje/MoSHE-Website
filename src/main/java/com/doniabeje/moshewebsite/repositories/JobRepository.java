package com.doniabeje.moshewebsite.repositories;

import com.doniabeje.moshewebsite.domains.Document;
import com.doniabeje.moshewebsite.domains.DocumentType;
import com.doniabeje.moshewebsite.domains.Job;
import com.doniabeje.moshewebsite.domains.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JobRepository extends PagingAndSortingRepository<Job, Long> {
    Iterable<Job> findByTitleContains(String title);
    Page<Job> findAllByLanguage(News.Language language, Pageable pageable);

}
