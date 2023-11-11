package com.cachenote.server.config;

import com.cachenote.server.utils.Snowflake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowFlakeConfig {

    @Bean
    public Snowflake snowflake() {
        //todo: the nodeID abd machineID should from environment
        long nodeId = 1;
        long machineId = 2;
        return new Snowflake(nodeId, machineId);
    }
}
