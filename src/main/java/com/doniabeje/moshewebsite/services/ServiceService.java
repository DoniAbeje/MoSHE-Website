package com.doniabeje.moshewebsite.services;

import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.domains.Service;
import com.doniabeje.moshewebsite.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ServiceService {
    public Service save(Service service);

    public Iterable<Service> saveAll(Iterable<Service> service);

    Optional<Service> findById(Long id);

    boolean existsById(Long id);

    Iterable<Service> findAll();
    Iterable<Service> findByTitleContains(String title);
    Page<Service> findAllByLanguage(News.Language language, PageRequest pageRequest);
    Page<Service> findAll(Pageable pageRequest);

    Iterable<Service> findAllById(Iterable<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(Service service);

    void deleteAll(Iterable<Service> service);

    void deleteAll();
}

@org.springframework.stereotype.Service
class ServiceServiceImpl implements ServiceService{
    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Iterable<Service> saveAll(Iterable<Service> service) {
        return serviceRepository.saveAll(service);
    }

    @Override
    public Optional<Service> findById(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return serviceRepository.existsById(id);
    }

    @Override
    public Iterable<Service> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Iterable<Service> findByTitleContains(String title) {
        return serviceRepository.findByTitleContains(title);
    }

    @Override
    public Page<Service> findAllByLanguage(News.Language language, PageRequest pageRequest) {
        return serviceRepository.findAllByLanguage(language, pageRequest);
    }

    @Override
    public Page<Service> findAll(Pageable pageRequest) {
        return serviceRepository.findAll(pageRequest);
    }

    @Override
    public Iterable<Service> findAllById(Iterable<Long> ids) {
        return serviceRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return serviceRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public void delete(Service service) {
        serviceRepository.delete(service);
    }

    @Override
    public void deleteAll(Iterable<Service> service) {
        serviceRepository.deleteAll(service);
    }

    @Override
    public void deleteAll() {
        serviceRepository.deleteAll();
    }
}