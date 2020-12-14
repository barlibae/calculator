package com.calculator.service;

import com.calculator.exception.InvalidExpressionException;
import com.calculator.model.Expression;
import com.calculator.model.Operand;
import com.calculator.model.Operator;
import com.calculator.model.Symbol;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import static com.calculator.model.Parenthesis.CLOSED;
import static com.calculator.model.Parenthesis.OPEN;

/**
 * Evaluates a mathematical expression in infix notation with full-parenthesized operands.
 */
public class InfixCalculator implements Calculator {

    public Integer calculate(List<Symbol> symbols) {

        try {
            return doCalculate(symbols);
        } catch (Exception e) {
            throw new InvalidExpressionException("Invalid expression");
        }
    }

    private Integer doCalculate(List<Symbol> symbols) {

        Deque<Symbol> operationsDeque = new ArrayDeque<>();
        Iterator<Symbol> iterator = symbols.iterator();
        while (iterator.hasNext()) {

            Symbol symbol = iterator.next();
            if (CLOSED == symbol) {
                operationsDeque.push(createExpression(operationsDeque));
            } else {
                operationsDeque.push(symbol);
            }
        }

        while (operationsDeque.size() > 1) {
            operationsDeque.push(createExpression(operationsDeque));
        }

        return ((Operand) operationsDeque.pop()).getValue();
    }

    private Expression createExpression(Deque<Symbol> operationsDeque) {

        Operand rightOperand = (Operand) operationsDeque.pop();
        Operator operator = (Operator) operationsDeque.pop();
        Operand leftOperand = (Operand) operationsDeque.pop();

        if (OPEN != operationsDeque.pop()) {
            throw new InvalidExpressionException("The parentheses do not match");
        }

        return new Expression(leftOperand, rightOperand, operator);
    }
}
