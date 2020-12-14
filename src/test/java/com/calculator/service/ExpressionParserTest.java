package com.calculator.service;

import com.calculator.exception.InvalidExpressionException;
import com.calculator.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExpressionParserTest {

    @InjectMocks
    private ExpressionParser parser;

    @Test
    void givenSimpleIntegerExpressionWhenParseThenReturnsTheInteger() {

        List<Symbol> expectedSymbols = List.of(new SimpleOperand(512));

        assertEquals(expectedSymbols, parser.parse(" 512"));
    }

    @Test
    void givenValidPrefixExpressionWhenParseThenReturnsExpectedSymbols() {

        List<Symbol> expectedSymbols = List.of(Operator.DIVISION,
                Operator.MINUS,
                new SimpleOperand(15),
                new SimpleOperand(10),
                new SimpleOperand(5));

        assertEquals(expectedSymbols, parser.parse(" / - 15 10 5"));
    }

    @Test
    void givenValidInfixExpressionWhenParseThenReturnsExpectedSymbols() {

        List<Symbol> expectedSymbols = List.of(Parenthesis.OPEN,
                new SimpleOperand(1),
                Operator.PLUS,
                Parenthesis.OPEN,
                new SimpleOperand(2),
                Operator.MULTIPLICATION,
                new SimpleOperand(3),
                Parenthesis.CLOSED,
                Parenthesis.CLOSED);

        assertEquals(expectedSymbols, parser.parse(" ( 1 + ( 2 * 3 ) )"));
    }

    @Test
    void givenInvalidExpressionWhenParseThenThrowsInvalidExpressionException() {

        assertThrows(InvalidExpressionException.class, () -> parser.parse("this is not a valid expression"));
    }
}