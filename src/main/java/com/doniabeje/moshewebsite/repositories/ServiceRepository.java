package com.doniabeje.moshewebsite.repositories;

import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.domains.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ServiceRepository extends PagingAndSortingRepository<Service, Long> {
    Iterable<Service> findByTitleContains(String title);
    Page<Service> findAllByLanguage(News.Language language, Pageable pageable);
}
