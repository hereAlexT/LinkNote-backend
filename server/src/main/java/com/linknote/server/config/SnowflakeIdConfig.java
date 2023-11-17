package com.linknote.server.config;

import com.linknote.server.utils.SnowflakeId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class SnowflakeIdConfig {

    @Bean
    public SnowflakeId snowflake(@Value("${app.snowflakeId.nodeId}") Long nodeId,
                                 @Value("${app.snowflakeId.machineId}") Long machineId) {
        return new SnowflakeId(nodeId, machineId);
    }
}
