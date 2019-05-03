package com.doniabeje.moshewebsite.repositories;

import com.doniabeje.moshewebsite.domains.Document;
import com.doniabeje.moshewebsite.domains.DocumentType;
import com.doniabeje.moshewebsite.domains.News;
import com.doniabeje.moshewebsite.domains.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    Page<Person> findAllByLanguage(News.Language language, Pageable pageable);
}
