package com.cachenote.server.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class SnowflakeTest {

    private Snowflake snowflake;

    @BeforeEach
    void setUp() {
        snowflake = new Snowflake(2, 3);
    }

    @Test
    void nextId_ShouldGenerateUniqueIds() {
        long firstId = snowflake.nextId();
        long secondId = snowflake.nextId();
        Assertions.assertNotEquals(firstId, secondId, "Generated IDs should be unique");
    }

    @Test
    void nextId_ShouldGenerateIncreasingIds() {
        long firstId = snowflake.nextId();
        long secondId = snowflake.nextId();
        Assertions.assertTrue(secondId > firstId, "Generated IDs should be increasing");
    }


}