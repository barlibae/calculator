package com.calculator.service;

import com.calculator.exception.InvalidExpressionException;
import com.calculator.model.SimpleOperand;
import com.calculator.model.Symbol;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.calculator.model.Operator.*;
import static com.calculator.model.Operator.PLUS;
import static com.calculator.model.Parenthesis.CLOSED;
import static com.calculator.model.Parenthesis.OPEN;
import static org.junit.jupiter.api.Assertions.*;

class PrefixCalculatorTest {

    private final PrefixCalculator calculator = new PrefixCalculator();

    @Test
    void givenBasicExpressionWhenCalculateThenReturnsCorrectResult() {

        // 3 = 3
        List<Symbol> symbols = List.of(new SimpleOperand(3));

        assertEquals(3, calculator.calculate(symbols));
    }

    @Test
    void givenValidExpressionWhenCalculateThenReturnsCorrectResult() {

        // - / 10 + 1 1 * 1 2 = 3
        List<Symbol> symbols = List.of(
                MINUS,
                DIVISION,
                new SimpleOperand(10),
                PLUS,
                new SimpleOperand(1),
                new SimpleOperand(1),
                MULTIPLICATION,
                new SimpleOperand(1),
                new SimpleOperand(2));

        assertEquals(3, calculator.calculate(symbols));
    }

    @Test
    void givenInvalidExpressionWithMissingOperandWhenCalculateThenThrowsInvalidExpressionException() {

        // / 2
        List<Symbol> symbols = List.of(DIVISION, new SimpleOperand(2));

        assertThrows(InvalidExpressionException.class, () -> calculator.calculate(symbols));
    }

    @Test
    void givenInvalidExpressionWithOnlyOperatorsWhenCalculateThenThrowsInvalidExpressionException() {

        // * + /
        List<Symbol> symbols = List.of(OPEN, PLUS, CLOSED);

        assertThrows(InvalidExpressionException.class, () -> calculator.calculate(symbols));
    }

    @Test
    void givenInvalidExpressionWithTooManyOperandsWhenCalculateThenThrowsInvalidExpressionException() {

        // - 2 3 4
        List<Symbol> symbols = List.of(MINUS, new SimpleOperand(2), new SimpleOperand(3), new SimpleOperand(4));

        assertThrows(InvalidExpressionException.class, () -> calculator.calculate(symbols));
    }
}