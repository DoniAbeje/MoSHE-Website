package com.doniabeje.moshewebsite.repositories;


import com.doniabeje.moshewebsite.domains.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String role);
}
