package com.doniabeje.moshewebsite.repositories;

import com.doniabeje.moshewebsite.domains.Document;
import com.doniabeje.moshewebsite.domains.DocumentType;
import com.doniabeje.moshewebsite.domains.GeneralInforamtion;
import com.doniabeje.moshewebsite.domains.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GeneralInformationRepository extends PagingAndSortingRepository<GeneralInforamtion, Long> {

}
