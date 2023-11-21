package com.linknote.server.payload.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.LocalDateTime;


@RedisHash("token")
@Data
@AllArgsConstructor
public class TokenCache {
    @Id
    private String token;
    private String userId;

    @TimeToLive
    private LocalDateTime expiredDatetime; //TTL in seconds


}
