package com.linknote.server.security.service;

import com.linknote.server.payload.entity.User;
import com.linknote.server.repository.UserRepository;
import com.linknote.server.security.entity.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found by username");
        }
        return new UserDetailsImpl(user.get());
    }

    public UserDetailsImpl loadUserByUserId(Long userId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found by user_id");
        }
        return new UserDetailsImpl(user.get());
    }

}
