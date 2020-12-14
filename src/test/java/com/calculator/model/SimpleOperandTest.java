package com.calculator.model;

import org.junit.jupiter.api.Test;

import static com.calculator.model.SymbolType.OPERAND;
import static org.junit.jupiter.api.Assertions.*;

class SimpleOperandTest {

    @Test
    void givenSimpleOperandWhenGetValueThenReturnsTheValue() {

        int value = 5;
        assertEquals(value, new SimpleOperand(value).getValue());
    }

    @Test
    void givenSimpleOperandWhenGetSymbolTypeThenReturnsOperandSymbolType() {

        assertEquals(OPERAND, new SimpleOperand(3).getType());
    }
}