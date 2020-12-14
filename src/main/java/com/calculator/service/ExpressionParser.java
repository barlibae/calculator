package com.calculator.service;

import com.calculator.exception.InvalidExpressionException;
import com.calculator.model.*;

import java.util.ArrayList;
import java.util.List;

import static com.calculator.model.Operator.isOperator;
import static com.calculator.model.Parenthesis.isParentheses;

/**
 * Parses a numerical expression into a list of {@link Symbol}
 */
public class ExpressionParser {

    public List<Symbol> parse(String expression) {

        int i = 0;
        List<Symbol> symbols = new ArrayList<>();
        while (i < expression.length()) {

            char c = expression.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            Symbol symbol;

            if (Character.isDigit(c)) {
                String value = IntegerMatcher.getMatch(expression.substring(i));
                symbol = new SimpleOperand(Integer.parseInt(value));
                i += value.length();

            } else if (isParentheses(c)) {
                symbol = Parenthesis.fromSign(expression.charAt(i));
                i++;

            } else if (isOperator(c)) {
                symbol = Operator.fromSign(expression.charAt(i));
                i++;

            } else {
                throw new InvalidExpressionException("Unknown symbol " + c);
            }
            symbols.add(symbol);
        }

        return symbols;
    }
}
