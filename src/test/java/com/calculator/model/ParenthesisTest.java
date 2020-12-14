package com.calculator.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.calculator.model.SymbolType.PARENTHESIS;
import static org.junit.jupiter.api.Assertions.*;

class ParenthesisTest {

    @ParameterizedTest
    @EnumSource(Parenthesis.class)
    void givenValidParenthesisSignWhenFromSignThenReturnsExpectedParenthesis(Parenthesis parenthesis) {

        assertEquals(parenthesis, Parenthesis.fromSign(parenthesis.getSign()));
    }

    @ParameterizedTest
    @ValueSource(chars = {'$', '~', '@'})
    void givenInvalidSignWhenFromSignThenThrowsIllegalArgumentException(char c) {

        assertThrows(IllegalArgumentException.class, () -> Parenthesis.fromSign(c));
    }

    @ParameterizedTest
    @EnumSource(Parenthesis.class)
    void givenValidParenthesisSignWhenIsParenthesisThenReturnTrue(Parenthesis parenthesis) {

        assertTrue(Parenthesis.isParentheses(parenthesis.getSign()));
    }

    @ParameterizedTest
    @ValueSource(chars = {'[', '~', '-'})
    void givenOtherSignsWhenIsParenthesisThenReturnsFalse(char c) {

        assertFalse(Parenthesis.isParentheses(c));
    }

    @ParameterizedTest
    @EnumSource(Parenthesis.class)
    void givenParenthesisWhenGetSymbolTypeThenReturnsParenthesisSymbolType(Parenthesis parenthesis) {

        assertEquals(PARENTHESIS, parenthesis.getType());
    }
}