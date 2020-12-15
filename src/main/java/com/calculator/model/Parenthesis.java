package com.calculator.model;

import java.util.Arrays;

public enum Parenthesis implements Symbol {

    OPEN('('),
    CLOSED(')');

    private final char sign;

    Parenthesis(char sign) {
        this.sign = sign;
    }

    public static Parenthesis fromSign(char sign) {

        for (Parenthesis p : values()) {
            if (sign == p.sign) {
                return p;
            }
        }
        throw new IllegalArgumentException("No enum constant for " + sign);
    }

    public char getSign() {
        return sign;
    }

    public static boolean isParentheses(char c) {
        return Arrays.stream(values()).anyMatch(p -> p.sign == c);
    }

    @Override
    public SymbolType getType() {
        return SymbolType.PARENTHESIS;
    }
}
