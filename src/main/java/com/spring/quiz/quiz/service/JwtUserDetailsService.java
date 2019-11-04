package com.spring.quiz.quiz.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.spring.quiz.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<com.spring.quiz.quiz.model.User> userList = userRepository.findAll();
        for (int i = 0; i < userList.size(); i++) {
            com.spring.quiz.quiz.model.User u = userList.get(i);
            if(u.getEmail().equals(username)) {
//                ArrayList grantedAuthorities =new ArrayList<String>();
//                grantedAuthorities.add(u.getRole());
                HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(1);
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + u.getRole()));
                return new User(u.getEmail(), u.getPassword(), authorities);
//                return new User(u.getEmail(), u.getPassword(), new ArrayList<>());
            }
        }

        throw new UsernameNotFoundException("User not found with username: " + username);

//        if ("javainuse".equals(username)) {
//            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
    }
}