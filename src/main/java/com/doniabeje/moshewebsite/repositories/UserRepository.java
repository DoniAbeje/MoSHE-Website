package com.doniabeje.moshewebsite.repositories;

import com.doniabeje.moshewebsite.domains.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String userName);
    User findByEmail(String email);
}
