package com.doniabeje.moshewebsite.services;

import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.domains.Tender;
import com.doniabeje.moshewebsite.repositories.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

public interface TenderService {
    public Tender save(Tender tender);

    public Iterable<Tender> saveAll(Iterable<Tender> tenders);

    Optional<Tender> findById(Long id);

    boolean existsById(Long id);

    Iterable<Tender> findAll();
    Iterable<Tender> findByTitleContains(String title);
    Page<Tender> findAllByDeadlineBefore(Date date, PageRequest pageRequest);
    Page<Tender> findAllByDeadlineAfter(Date date, Pageable pageable);
    Page<Tender> findAllByDeadlineBeforeAndLanguage(Date date, News.Language language, Pageable pageable);
    Page<Tender> findAllByDeadlineAfterAndLanguage(Date date, News.Language language, Pageable pageable);
    Page<Tender> findAll(Pageable pageRequest);

    Iterable<Tender> findAllById(Iterable<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(Tender tender);

    void deleteAll(Iterable<Tender> tenders);

    void deleteAll();
}

@Service
class TenderServiceImpl implements TenderService{
    @Autowired
    TenderRepository tenderRepository;

    @Override
    public Tender save(Tender tender) {
        return tenderRepository.save(tender);
    }

    @Override
    public Iterable<Tender> saveAll(Iterable<Tender> tenders) {
        return tenderRepository.saveAll(tenders);
    }

    @Override
    public Optional<Tender> findById(Long id) {
        return tenderRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return tenderRepository.existsById(id);
    }

    @Override
    public Iterable<Tender> findAll() {
        return tenderRepository.findAll();
    }

    @Override
    public Iterable<Tender> findByTitleContains(String title) {
        return tenderRepository.findByTitleContains(title);
    }

    @Override
    public Page<Tender> findAllByDeadlineBefore(Date date, PageRequest pageRequest) {
        return tenderRepository.findAllByDeadlineBefore(date, pageRequest);
    }

    @Override
    public Page<Tender> findAllByDeadlineAfter(Date date, Pageable pageable) {
        return tenderRepository.findAllByDeadlineAfter(date, pageable);
    }

    @Override
    public Page<Tender> findAllByDeadlineBeforeAndLanguage(Date date, News.Language language, Pageable pageable) {
        return tenderRepository.findAllByDeadlineBeforeAndLanguage(date, language, pageable);
    }

    @Override
    public Page<Tender> findAllByDeadlineAfterAndLanguage(Date date, News.Language language, Pageable pageable) {
        return tenderRepository.findAllByDeadlineAfterAndLanguage(date, language, pageable);
    }

    @Override
    public Page<Tender> findAll(Pageable pageRequest) {
        return tenderRepository.findAll(pageRequest);
    }

    @Override
    public Iterable<Tender> findAllById(Iterable<Long> ids) {
        return tenderRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return tenderRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        tenderRepository.deleteById(id);
    }

    @Override
    public void delete(Tender tender) {
        tenderRepository.delete(tender);
    }

    @Override
    public void deleteAll(Iterable<Tender> tenders) {
        tenderRepository.deleteAll(tenders);
    }

    @Override
    public void deleteAll() {
        tenderRepository.deleteAll();
    }
}