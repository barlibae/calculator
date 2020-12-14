package com.calculator.model;

import com.calculator.exception.InvalidExpressionException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.calculator.model.NotationType.INFIX;
import static com.calculator.model.NotationType.PREFIX;
import static java.util.Collections.EMPTY_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotationTypeTest {

    @Test
    void givenASymbolsListStartingWithParenthesisWhenGetNotationTypeThenReturnsInfixNotation() {

        assertEquals(INFIX, NotationType.getType(List.of(Parenthesis.OPEN, new SimpleOperand(2))));
    }

    @Test
    void givenASymbolsListStartingWithAnOperatorWhenGetNotationTypeThenReturnsPrefixNotation() {

        assertEquals(PREFIX, NotationType.getType(List.of(Operator.PLUS)));
    }

    @Test
    void givenNullSymbolsListWhenGetNotationTypeThenThrowsInvalidExpressionException() {

        assertThrows(InvalidExpressionException.class, () -> NotationType.getType(null));
    }

    @Test
    void givenEmptySymbolsListWhenGetNotationTypeThenThrowsInvalidExpressionException() {

        assertThrows(InvalidExpressionException.class, () -> NotationType.getType(EMPTY_LIST));
    }
}