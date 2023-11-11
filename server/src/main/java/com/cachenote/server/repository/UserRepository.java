package com.cachenote.server.repository;


import com.cachenote.server.payload.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository extends JpaRepository<User, Long> {


    //todo: set to Optional<User> findByUsername(String username);
    User findByUsername(String username);


}