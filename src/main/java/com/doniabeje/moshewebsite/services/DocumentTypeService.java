package com.doniabeje.moshewebsite.services;

import com.doniabeje.moshewebsite.domains.DocumentType;
import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.repositories.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface DocumentTypeService {
    public DocumentType save(DocumentType documentType);

    public Iterable<DocumentType> saveAll(Iterable<DocumentType> documentTypes);

    Optional<DocumentType> findById(Long id);

    boolean existsById(Long id);

    Iterable<DocumentType> findAll();
    Iterable<DocumentType> findByTitleContains(String title);
    Page<DocumentType> findAll(Pageable pageRequest);
    Page<DocumentType> findAllByLanguage(News.Language language, Pageable pageable);

    Iterable<DocumentType> findAllById(Iterable<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(DocumentType documentType);

    void deleteAll(Iterable<DocumentType> documentTypes);

    void deleteAll();
}

@Service
class DocumentTypeServiceImpl implements DocumentTypeService{
    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Override
    public DocumentType save(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    @Override
    public Iterable<DocumentType> saveAll(Iterable<DocumentType> documentTypes) {
        return documentTypeRepository.saveAll(documentTypes);
    }

    @Override
    public Optional<DocumentType> findById(Long id) {
        return documentTypeRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return documentTypeRepository.existsById(id);
    }

    @Override
    public Iterable<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }

    @Override
    public Iterable<DocumentType> findByTitleContains(String title) {
        return documentTypeRepository.findByTitleContains(title);
    }

    @Override
    public Page<DocumentType> findAll(Pageable pageRequest) {
        return documentTypeRepository.findAll(pageRequest);
    }

    @Override
    public Page<DocumentType> findAllByLanguage(News.Language language, Pageable pageable) {
        return documentTypeRepository.findAllByLanguage(language, pageable);
    }

    @Override
    public Iterable<DocumentType> findAllById(Iterable<Long> ids) {
        return documentTypeRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return documentTypeRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        documentTypeRepository.deleteById(id);
    }

    @Override
    public void delete(DocumentType documentType) {
        documentTypeRepository.delete(documentType);
    }

    @Override
    public void deleteAll(Iterable<DocumentType> documentTypes) {
        documentTypeRepository.deleteAll(documentTypes);
    }

    @Override
    public void deleteAll() {
        documentTypeRepository.deleteAll();
    }
}