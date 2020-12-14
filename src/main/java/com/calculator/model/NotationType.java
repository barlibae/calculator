package com.calculator.model;

import com.calculator.exception.InvalidExpressionException;

import java.util.List;

import static com.calculator.model.SymbolType.PARENTHESIS;

public enum NotationType {

    INFIX,
    PREFIX;

    public static NotationType getType(List<Symbol> symbols) {

        if (symbols == null || symbols.isEmpty()) {
            throw new InvalidExpressionException("The expression must not be empty");
        }

        return PARENTHESIS == symbols.get(0).getType() ? INFIX : PREFIX;
    }
}
