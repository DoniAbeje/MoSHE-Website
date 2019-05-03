package com.doniabeje.moshewebsite.repositories;

import com.doniabeje.moshewebsite.domains.NewsImage;
import com.doniabeje.moshewebsite.domains.Vacancy;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NewsImageRepository extends PagingAndSortingRepository<NewsImage, Long> {

}
