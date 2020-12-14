package com.calculator.service;

import com.calculator.exception.InvalidExpressionException;
import com.calculator.model.SimpleOperand;
import com.calculator.model.Symbol;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.calculator.model.Operator.*;
import static com.calculator.model.Parenthesis.CLOSED;
import static com.calculator.model.Parenthesis.OPEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InfixCalculatorTest {

    private final InfixCalculator calculator = new InfixCalculator();

    @Test
    void givenBasicExpressionWhenCalculateThenReturnsCorrectResult() {

        // ( 1 + 2 ) = 3
        List<Symbol> symbols = List.of(OPEN, new SimpleOperand(1), PLUS, new SimpleOperand(2), CLOSED);

        assertEquals(3, calculator.calculate(symbols));
    }

    @Test
    void givenValidExpressionWhenCalculateThenReturnsCorrectResult() {

        // ( ( ( 1 + 1 ) / 10 ) - ( 1 * 2 ) ) = -2
        List<Symbol> symbols = List.of(
                OPEN, OPEN,
                OPEN, new SimpleOperand(1), PLUS, new SimpleOperand(1), CLOSED,
                DIVISION, new SimpleOperand(10),
                CLOSED,
                MINUS,
                OPEN, new SimpleOperand(1), MULTIPLICATION, new SimpleOperand(2), CLOSED,
                CLOSED);

        assertEquals(-2, calculator.calculate(symbols));
    }

    @Test
    void givenInvalidExpressionWithMissingOperandWhenCalculateThenThrowsInvalidExpressionException() {

        // ( 1 + )
        List<Symbol> symbols = List.of(OPEN, new SimpleOperand(1), PLUS, OPEN);

        assertThrows(InvalidExpressionException.class, () -> calculator.calculate(symbols));
    }

    @Test
    void givenInvalidExpressionWithOnlyOperatorWhenCalculateThenThrowsInvalidExpressionException() {

        // ( + )
        List<Symbol> symbols = List.of(OPEN, PLUS, CLOSED);

        assertThrows(InvalidExpressionException.class, () -> calculator.calculate(symbols));
    }
}