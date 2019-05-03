package com.doniabeje.moshewebsite.repositories;

import com.doniabeje.moshewebsite.domains.Suggestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SuggestionRepository extends PagingAndSortingRepository<Suggestion, Long> {
  
}
