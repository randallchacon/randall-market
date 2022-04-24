package com.randall.market.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RandallUserDetailsService implements UserDetailsService{
        private static List<User> users = new ArrayList();
        //En una aplicación real, en lugar de usar datos locales, se debe de consultar los usuarios de la base de datos o un sistema externo
        public RandallUserDetailsService(){
        users.add(new User("randall", "{noop}randallAPI", new ArrayList<>()));
        }

@Override
public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        Optional<User> user = users.stream().filter(u -> u.getUsername().equals(username)).findAny();

        if (!user.isPresent()) {
        throw new UsernameNotFoundException("User not found by name: " + username);
        }

        return user.get();
        }
}

/*@Service
public class RandallUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("randall", "{noop}randallAPI", new ArrayList<>());
    }
}*/
