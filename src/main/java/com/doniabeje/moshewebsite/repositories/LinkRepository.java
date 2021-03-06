package com.doniabeje.moshewebsite.repositories;

import com.doniabeje.moshewebsite.domains.Link;
import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.domains.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LinkRepository extends PagingAndSortingRepository<Link, Long> {
    Iterable<Link> findByTitleContains(String title);
    Page<Link> findAllByLanguage(News.Language language, Pageable pageable);
}
