package com.doniabeje.moshewebsite.services;

import com.doniabeje.moshewebsite.domains.Document;
import com.doniabeje.moshewebsite.domains.DocumentType;
import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface DocumentService {
    public Document save(Document document);

    public Iterable<Document> saveAll(Iterable<Document> documents);

    Optional<Document> findById(Long id);

    boolean existsById(Long id);

    Iterable<Document> findAll();
    Iterable<Document> findByTitleContains(String title);
    Page<Document> findAll(Pageable pageRequest);
    Page<Document> findAllByLanguage(News.Language language, Pageable pageable);
    Page<Document> findAllByDocumentTypeAndLanguage(DocumentType documentType, News.Language language, Pageable pageable);

    Iterable<Document> findAllById(Iterable<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(Document document);

    void deleteAll(Iterable<Document> documents);

    void deleteAll();
}

@Service
class DocumentServiceImpl implements DocumentService{
    @Autowired
    DocumentRepository documentRepository;

    @Override
    public Document save(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Iterable<Document> saveAll(Iterable<Document> documents) {
        return documentRepository.saveAll(documents);
    }

    @Override
    public Optional<Document> findById(Long id) {
        return documentRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return documentRepository.existsById(id);
    }

    @Override
    public Iterable<Document> findAll() {
        return documentRepository.findAll();
    }

    @Override
    public Iterable<Document> findByTitleContains(String title) {
        return documentRepository.findByTitleContains(title);
    }

    @Override
    public Page<Document> findAll(Pageable pageRequest) {
        return documentRepository.findAll(pageRequest);
    }

    @Override
    public Page<Document> findAllByLanguage(News.Language language, Pageable pageable) {
        return documentRepository.findAllByLanguage(language, pageable);
    }

    @Override
    public Page<Document> findAllByDocumentTypeAndLanguage(DocumentType documentType, News.Language language, Pageable pageable) {
        return documentRepository.findAllByDocumentTypeAndLanguage(documentType, language, pageable);
    }

    @Override
    public Iterable<Document> findAllById(Iterable<Long> ids) {
        return documentRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return documentRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        documentRepository.deleteById(id);
    }

    @Override
    public void delete(Document document) {
        documentRepository.delete(document);
    }

    @Override
    public void deleteAll(Iterable<Document> documents) {
        documentRepository.deleteAll(documents);
    }

    @Override
    public void deleteAll() {
        documentRepository.deleteAll();
    }
}