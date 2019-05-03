package com.doniabeje.moshewebsite.repositories;

import com.doniabeje.moshewebsite.domains.Document;
import com.doniabeje.moshewebsite.domains.DocumentType;
import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.domains.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DocumentRepository extends PagingAndSortingRepository<Document, Long> {
    Iterable<Document> findByTitleContains(String title);
    Page<Document> findAllByLanguage(News.Language language, Pageable pageable);
    Page<Document> findAllByDocumentTypeAndLanguage(DocumentType documentType, News.Language language, Pageable pageable);
}
