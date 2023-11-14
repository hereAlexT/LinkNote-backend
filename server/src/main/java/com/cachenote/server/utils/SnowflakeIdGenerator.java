package com.cachenote.server.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;


public class SnowflakeIdGenerator implements IdentifierGenerator{
    private final SnowflakeId snowflakeID;

    public SnowflakeIdGenerator(SnowflakeId snowflakeID) {
        this.snowflakeID = snowflakeID;
    }


    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return snowflakeID.nextId();
    }
}
