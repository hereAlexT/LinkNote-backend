package com.cachenote.server.payload.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;


@RedisHash("token")
@Data
@AllArgsConstructor
public class TokenCache {
    @Id
    private String token;
    private String username;

    @TimeToLive
    private long expiration; //TTL in seconds



}
