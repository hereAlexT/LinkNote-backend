package com.cachenote.server.repository;


import com.cachenote.server.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


public interface UserRepository extends MongoRepository<User, String>
{
    @Query("{username: '?0'}")
    public User findByUsername(String userName);
}
