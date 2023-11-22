package com.linknote.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    @Value("${app.version}")
    private String version;

    /**
     * This method should only work with logged-in user.
     * 
     * @return health status
     */
    @GetMapping()
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("health", true);
        response.put("version", version);
        return response;
    }

}
