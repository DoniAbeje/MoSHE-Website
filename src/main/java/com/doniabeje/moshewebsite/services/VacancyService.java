package com.doniabeje.moshewebsite.services;

import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.domains.Vacancy;
import com.doniabeje.moshewebsite.repositories.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

public interface VacancyService {
    public Vacancy save(Vacancy vacancy);

    public Iterable<Vacancy> saveAll(Iterable<Vacancy> vacancies);

    Optional<Vacancy> findById(Long id);

    boolean existsById(Long id);

    Iterable<Vacancy> findAll();
    Iterable<Vacancy> findByTitleContains(String title);
    Page<Vacancy> findAllByDeadlineBefore(Date date, Pageable pageable);
    Page<Vacancy> findAllByDeadlineAfter(Date date, Pageable pageable);

    Page<Vacancy> findAllByDeadlineBeforeAndLanguage(Date date, News.Language language, Pageable pageable);
    Page<Vacancy> findAllByDeadlineAfterAndLanguage(Date date, News.Language language, Pageable pageable);
    Page<Vacancy> findAll(Pageable pageRequest);

    Iterable<Vacancy> findAllById(Iterable<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(Vacancy vacancy);

    void deleteAll(Iterable<Vacancy> vacancies);

    void deleteAll();
}

@Service
class VacancyServiceImpl implements VacancyService{
    @Autowired
    VacancyRepository vacancyRepository;

    @Override
    public Vacancy save(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    @Override
    public Iterable<Vacancy> saveAll(Iterable<Vacancy> vacancies) {
        return vacancyRepository.saveAll(vacancies);
    }

    @Override
    public Optional<Vacancy> findById(Long id) {
        return vacancyRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return vacancyRepository.existsById(id);
    }

    @Override
    public Iterable<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }

    @Override
    public Iterable<Vacancy> findByTitleContains(String title) {
        return vacancyRepository.findByTitleContains(title);
    }

    @Override
    public Page<Vacancy> findAllByDeadlineBefore(Date date, Pageable pageable) {
        return vacancyRepository.findAllByDeadlineBefore(date, pageable);
    }

    @Override
    public Page<Vacancy> findAllByDeadlineAfter(Date date, Pageable pageable) {
        return vacancyRepository.findAllByDeadlineAfter(date, pageable);
    }

    @Override
    public Page<Vacancy> findAllByDeadlineBeforeAndLanguage(Date date, News.Language language, Pageable pageable) {
        return vacancyRepository.findAllByDeadlineBeforeAndLanguage(date, language, pageable);
    }

    @Override
    public Page<Vacancy> findAllByDeadlineAfterAndLanguage(Date date, News.Language language, Pageable pageable) {
        return vacancyRepository.findAllByDeadlineAfterAndLanguage(date, language, pageable);
    }


    @Override
    public Page<Vacancy> findAll(Pageable pageRequest) {
        return vacancyRepository.findAll(pageRequest);
    }

    @Override
    public Iterable<Vacancy> findAllById(Iterable<Long> ids) {
        return vacancyRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return vacancyRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        vacancyRepository.deleteById(id);
    }

    @Override
    public void delete(Vacancy vacancy) {
        vacancyRepository.delete(vacancy);
    }

    @Override
    public void deleteAll(Iterable<Vacancy> vacancies) {
        vacancyRepository.deleteAll(vacancies);
    }

    @Override
    public void deleteAll() {
        vacancyRepository.deleteAll();
    }
}