package com.calculator.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.calculator.model.Operator.*;
import static com.calculator.model.SymbolType.OPERATOR;
import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {

    @Test
    void givenPlusOperatorWhenApplyThenAddsValues() {

        assertEquals(5, PLUS.apply(new SimpleOperand(2), new SimpleOperand(3)).getValue());
    }

    @Test
    void givenMinusOperatorWhenApplyThenSubstractsValues() {

        assertEquals(-3, MINUS.apply(new SimpleOperand(2), new SimpleOperand(5)).getValue());
    }

    @Test
    void givenMultiplicationOperatorWhenApplyThenMultipliesValues() {

        assertEquals(15, MULTIPLICATION.apply(new SimpleOperand(3), new SimpleOperand(5)).getValue());
    }

    @Test
    void givenDivisionOperatorWhenApplyThenDividesValues() {

        assertEquals(5, DIVISION.apply(new SimpleOperand(25), new SimpleOperand(5)).getValue());
    }


    @ParameterizedTest
    @EnumSource(Operator.class)
    void givenValidOperatorSignWhenFromSignThenReturnsExpectedOperator(Operator operator) {

        assertEquals(operator, Operator.fromSign(operator.getSign()));
    }

    @ParameterizedTest
    @ValueSource(chars = {'%', '~', '3'})
    void givenInvalidSignWhenFromSignThenThrowsIllegalArgumentException(char c) {

        assertThrows(IllegalArgumentException.class, () -> Operator.fromSign(c));
    }

    @ParameterizedTest
    @EnumSource(Operator.class)
    void givenValidOperatorSignWhenIsOperatorThenReturnTrue(Operator operator) {

        assertTrue(Operator.isOperator(operator.getSign()));
    }

    @ParameterizedTest
    @ValueSource(chars = {'&', '~', '1'})
    void givenOtherSignsWhenIsOperatorThenReturnsFalse(char c) {

        assertFalse(Operator.isOperator(c));
    }

    @ParameterizedTest
    @EnumSource(Operator.class)
    void givenOperatorWhenGetSymbolTypeThenReturnsOperatorSymbolType(Operator operator) {

        assertEquals(OPERATOR, operator.getType());
    }
}