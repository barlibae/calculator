package com.calculator.service;

import com.calculator.exception.InvalidExpressionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerMatcherTest {

    @Test
    void givenIntegerWhenGetMatchThenReturnsTheExpectedResult() {

        assertEquals("123", IntegerMatcher.getMatch("123"));
    }

    @Test
    void givenIntegerFollowedByOtherCharactersWhenGetMatchThenReturnsTheExpectedResult() {

        assertEquals("123", IntegerMatcher.getMatch("123 + 256"));
    }

    @Test
    void givenInvalidStringWhenGetMatchThenThrowsInvalidExpressionException() {

        assertThrows(InvalidExpressionException.class, () -> IntegerMatcher.getMatch("abcd 123"));
    }
}