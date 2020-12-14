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

import static com.calculator.model.SymbolType.OPERAND;

/**
 * Evaluates a mathematical expression in prefix notation.
 */
public class PrefixCalculator implements Calculator {

    private static final int OPERATOR_ARITY = 2;

    @Override
    public Integer calculate(List<Symbol> symbols) {

        try {
            return doCalculate(symbols);
        } catch (Exception e) {
            throw new InvalidExpressionException("Invalid expression", e);
        }
    }

    private Integer doCalculate(List<Symbol> symbols) {

        Deque<Symbol> operationsDeque = new ArrayDeque<>();

        int numOperands = 0;
        Iterator<Symbol> iterator = symbols.iterator();
        while (iterator.hasNext()) {

            Symbol current = numOperands == OPERATOR_ARITY ? createExpression(operationsDeque) :  iterator.next();
            Symbol previous = operationsDeque.isEmpty() ? null : operationsDeque.peek();

            operationsDeque.push(current);
            numOperands = getNumOperands(current, previous);
        }

        while (operationsDeque.size() > 1) {
            operationsDeque.push(createExpression(operationsDeque));
        }

        return ((Operand) operationsDeque.pop()).getValue();
    }

    private int getNumOperands(Symbol current, Symbol previous) {

        if (!isOperand(current)) {
            return 0;
        }
        return isOperand(previous) ? 2 : 1;
    }

    private boolean isOperand(Symbol symbol) {

        return symbol != null && OPERAND == symbol.getType();
    }

    private Expression createExpression(Deque<Symbol> operationsDeque) {

        Operand rightOperand = (Operand) operationsDeque.pop();
        Operand leftOperand = (Operand) operationsDeque.pop();
        Operator operator = (Operator) operationsDeque.pop();

        return new Expression(leftOperand, rightOperand, operator);
    }
}
