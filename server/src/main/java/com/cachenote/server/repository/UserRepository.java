package com.cachenote.server.repository;


import com.cachenote.server.entity.UserDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends MongoRepository<UserDoc, String>
{
    @Query("{username: '?0'}")
    public UserDoc findByUsername(String username);
}
