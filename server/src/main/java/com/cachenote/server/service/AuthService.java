package com.cachenote.server.service;


import com.cachenote.server.entity.User;

public interface AuthService {

    /**
     * Finds account by username
     *
     * @param username
     * @return found User
     */
    User findByUsername(String username);


    /**
     * Checks if account with the same name already exists
     * Creates new account with Default parameters
     */
    User register(User user);


    /**
     * Check the password of user and return token
     *
     * @param user user
     * @return founded user
     */
    User login(User user);


}
