package com.doniabeje.moshewebsite.services;

import com.doniabeje.moshewebsite.domains.Job;
import com.doniabeje.moshewebsite.domains.Person;
import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface PersonService {
    public Person save(Person person);

    public Iterable<Person> saveAll(Iterable<Person> persons);

    Optional<Person> findById(Long id);

    boolean existsById(Long id);

    Iterable<Person> findAll();

    Page<Person> findAll(Pageable pageRequest);
    Page<Person> findAllByLanguage(News.Language language, Pageable pageable);
    Page<Person> findAllByJob(Job job, Pageable pageable);


    Iterable<Person> findAllById(Iterable<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(Person person);

    void deleteAll(Iterable<Person> persons);

    void deleteAll();
}

@Service
class PersonServiceImpl implements PersonService{
    @Autowired
    PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Iterable<Person> saveAll(Iterable<Person> persons) {
        return personRepository.saveAll(persons);
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return personRepository.existsById(id);
    }

    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }



    @Override
    public Page<Person> findAll(Pageable pageRequest) {
        return personRepository.findAll(pageRequest);
    }

    @Override
    public Page<Person> findAllByLanguage(News.Language language, Pageable pageable) {
        return personRepository.findAllByLanguage(language, pageable);
    }

    @Override
    public Page<Person> findAllByJob(Job job, Pageable pageable) {
        return personRepository.findAllByJob(job, pageable);
    }


    @Override
    public Iterable<Person> findAllById(Iterable<Long> ids) {
        return personRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return personRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Override
    public void deleteAll(Iterable<Person> persons) {
        personRepository.deleteAll(persons);
    }

    @Override
    public void deleteAll() {
        personRepository.deleteAll();
    }
}