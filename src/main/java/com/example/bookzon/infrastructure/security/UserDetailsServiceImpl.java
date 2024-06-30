package com.example.bookzon.infrastructure.security;

import com.example.bookzon.application.gateways.UserGateway;

import com.example.bookzon.domain.entities.User;
import com.example.bookzon.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserGateway userRepository;

    public UserDetailsServiceImpl(UserGateway userGateway) {
        this.userRepository = userGateway;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = (UserDetails) userRepository.findByUsername(username);

       if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
         return user;

    }
}