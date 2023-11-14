package com.cachenote.server.controller;




import com.cachenote.server.payload.entity.TokenCache;
import com.cachenote.server.repository.TokenCacheRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {


    private final TokenCacheRepository tokenCacheRepository;

    public TestController(TokenCacheRepository tokenCacheRepository) {
        this.tokenCacheRepository = tokenCacheRepository;
    }
    @GetMapping("health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("Health: True", HttpStatus.OK);
    }

    @GetMapping("wrtie-to-redis")
    public ResponseEntity<Void> writeToRedis() {
        TokenCache tokenCache = new TokenCache("token", "username", 99999);
        tokenCacheRepository.save(tokenCache);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}

