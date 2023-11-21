package com.linknote.server.utils;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Assertions;

class SnowflakeIdTest {

    private SnowflakeId snowflakeID;

    @BeforeEach
    void setUp() {
        snowflakeID = new SnowflakeId(2, 3);
    }

    @Test
    void nextId_ShouldGenerateUniqueIds() {
        long firstId = snowflakeID.nextId();
        long secondId = snowflakeID.nextId();
        Assertions.assertNotEquals(firstId, secondId, "Generated IDs should be unique");
    }

    @Test
    void nextId_ShouldGenerateIncreasingIds() {
        long firstId = snowflakeID.nextId();
        long secondId = snowflakeID.nextId();
        Assertions.assertTrue(secondId > firstId, "Generated IDs should be increasing");
    }


}