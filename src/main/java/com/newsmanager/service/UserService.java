package com.newsmanager.service;

import com.newsmanager.model.User;
import com.newsmanager.model.Role;
import com.newsmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import static org.springframework.security.core.userdetails.User.withUsername;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserBuilder builder = withUsername(user.getUsername());
        builder.password(user.getPassword());

        builder.authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));

        return builder.build();
    }

    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }
}
