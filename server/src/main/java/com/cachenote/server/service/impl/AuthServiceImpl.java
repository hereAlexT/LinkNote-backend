package com.cachenote.server.service.impl;

import com.cachenote.server.entity.User;
import com.cachenote.server.repository.UserRepository;

import com.cachenote.server.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AuthServiceImpl implements AuthService {


    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User register(User user) {
        return null;
    }

    @Override
    public User login(User user) {
        return null;
    }
}
