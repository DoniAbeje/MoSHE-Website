package com.doniabeje.moshewebsite.repositories;

import com.doniabeje.moshewebsite.domains.Document;
import com.doniabeje.moshewebsite.domains.DocumentType;
import com.doniabeje.moshewebsite.domains.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DocumentTypeRepository extends PagingAndSortingRepository<DocumentType, Long> {
    Iterable<DocumentType> findByTitleContains(String title);
    Page<DocumentType> findAllByLanguage(News.Language language, Pageable pageable);
}
