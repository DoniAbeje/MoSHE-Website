package com.doniabeje.moshewebsite.services;


import com.doniabeje.moshewebsite.domains.Role;
import com.doniabeje.moshewebsite.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface RoleService {
    Role findByName(String name);
    public Role save(Role role);

    public Iterable<Role> saveAll(Iterable<Role> roles);

    Optional<Role> findById(Long id);

    boolean existsById(Long id);

    Iterable<Role> findAll();

    Iterable<Role> findAllById(Iterable<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(Role role);

    void deleteAll(Iterable<Role> roles);

    void deleteAll();
}

@Service
class RoleServiceImp implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {

        return roleRepository.findByName(name);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Iterable<Role> saveAll(Iterable<Role> roles) {
        return roleRepository.saveAll(roles);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return roleRepository.existsById(id);
    }

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Iterable<Role> findAllById(Iterable<Long> ids) {
        return roleRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return roleRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void deleteAll(Iterable<Role> roles) {
        roleRepository.deleteAll(roles);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }


}