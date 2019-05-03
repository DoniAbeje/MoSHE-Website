package com.doniabeje.moshewebsite.services;

import com.doniabeje.moshewebsite.domains.Job;
import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface JobService {
    public Job save(Job job);

    public Iterable<Job> saveAll(Iterable<Job> jobs);

    Optional<Job> findById(Long id);

    boolean existsById(Long id);

    Iterable<Job> findAll();
    Iterable<Job> findByTitleContains(String title);
    Page<Job> findAll(Pageable pageRequest);
    Page<Job> findAllByLanguage(News.Language language, Pageable pageable);


    Iterable<Job> findAllById(Iterable<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(Job job);

    void deleteAll(Iterable<Job> jobs);

    void deleteAll();
}

@Service
class JobServiceImpl implements JobService{
    @Autowired
    JobRepository jobRepository;

    @Override
    public Job save(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Iterable<Job> saveAll(Iterable<Job> jobs) {
        return jobRepository.saveAll(jobs);
    }

    @Override
    public Optional<Job> findById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jobRepository.existsById(id);
    }

    @Override
    public Iterable<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Iterable<Job> findByTitleContains(String title) {
        return jobRepository.findByTitleContains(title);
    }

    @Override
    public Page<Job> findAll(Pageable pageRequest) {
        return jobRepository.findAll(pageRequest);
    }

    @Override
    public Page<Job> findAllByLanguage(News.Language language, Pageable pageable) {
        return jobRepository.findAllByLanguage(language, pageable);
    }



    @Override
    public Iterable<Job> findAllById(Iterable<Long> ids) {
        return jobRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return jobRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public void delete(Job job) {
        jobRepository.delete(job);
    }

    @Override
    public void deleteAll(Iterable<Job> jobs) {
        jobRepository.deleteAll(jobs);
    }

    @Override
    public void deleteAll() {
        jobRepository.deleteAll();
    }
}