package com.cachenote.server.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {


    /**
     * This method should only work with logged-in user.
     * @return health status
     */
    @GetMapping()
    public Map<String, Boolean> health() {
        return Collections.singletonMap("health", true);
    }

}
