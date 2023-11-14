package com.cachenote.server.repository;


import com.cachenote.server.payload.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository extends JpaRepository<User, Long> {


    //todo: set to Optional<User> findByUsername(String username);
    User findByEmail(String email);


}