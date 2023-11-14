package com.cachenote.server.config;

import com.cachenote.server.utils.SnowflakeId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowflakeIdConfig {

    @Bean
    public SnowflakeId snowflake() {
        //todo: the nodeID abd machineID should from environment
        long nodeId = 1;
        long machineId = 2;
        return new SnowflakeId(nodeId, machineId);
    }
}
