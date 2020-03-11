package com.doniabeje.moshewebsite.services;

import com.doniabeje.moshewebsite.domains.User;
import com.doniabeje.moshewebsite.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService {

    User findUserByUsername(String username);
    User findUserByEmail(String email);
    void saveUser(User user);

}

@Service
class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }


}