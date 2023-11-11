package com.cachenote.server.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;


public class SnowflakeGenerator implements IdentifierGenerator{
    private final Snowflake snowflake;

    public SnowflakeGenerator(Snowflake snowflake) {
        this.snowflake = snowflake;
    }


    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return snowflake.nextId();
    }
}
