package com.cachenote.server.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {


    /**
     * This method should only work with logged-in user.
     * @return health status
     */
    @GetMapping()
    public ResponseEntity<Object> health() {
        return ResponseEntity.ok().body(new Object() {
            public final boolean health = true;
        });
    }

}
