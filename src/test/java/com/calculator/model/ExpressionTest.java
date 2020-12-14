package com.calculator.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.calculator.model.Operator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ExpressionTest {

    @Test
    void givenSimpleOperandsWhenGetValueThenAppliesTheOperator() {

        Operand left = new SimpleOperand(10);
        Operand right = new SimpleOperand(7);

        assertEquals(3, new Expression(left, right, MINUS).getValue());
    }

    @Test
    void givenExpressionOperandsWhenGetValueThenAppliesEvaluatesAllExpressions() {

        Operand left = new Expression(new SimpleOperand(2), new SimpleOperand(2), MULTIPLICATION); // 4
        Operand right = new Expression(new SimpleOperand(1), new SimpleOperand(1), PLUS); // 2

        int expectedResult = 2; // (4 / 2 = 2)
        assertEquals(expectedResult, new Expression(left, right, DIVISION).getValue());
    }
}