package com.eci.arsw.project.unite.beans.impl;

import com.eci.arsw.project.unite.repository.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsersRepository applicationUserRepository;

    public UserDetailsServiceImpl(UsersRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.eci.arsw.project.unite.model.User> applicationUser = applicationUserRepository.findById(username);
        if (!applicationUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.get().getUsername(), applicationUser.get().getPassword(), emptyList());
    }
}