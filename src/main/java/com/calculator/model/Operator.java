package com.calculator.model;

import java.util.Arrays;

/**
 * A binary arithmetic operator, see the {@link Operator#apply(Operand, Operand)} method
 */
public enum Operator implements Symbol {

    PLUS('+'),
    MINUS('-'),
    MULTIPLICATION('*'),
    DIVISION('/');

    private final char sign;

    Operator(char sign) {
        this.sign = sign;
    }

    /**
     * Applies the operator to the given operands
     *
     * @param left  the left operand
     * @param right the right operand
     * @return the result of the operation
     */
    public Operand apply(Operand left, Operand right) {

        int result = switch (this) {
            case PLUS -> left.getValue() + right.getValue();
            case MINUS -> left.getValue() - right.getValue();
            case DIVISION -> left.getValue() / right.getValue();
            case MULTIPLICATION -> left.getValue() * right.getValue();
        };

        return new SimpleOperand(result);
    }

    public static Operator fromSign(char sign) {

        for (Operator o : values()) {
            if (sign == o.sign) {
                return o;
            }
        }
        throw new IllegalArgumentException("No enum constant for " + sign);
    }

    public char getSign() {
        return sign;
    }

    public static boolean isOperator(char sign) {
        return Arrays.stream(values()).anyMatch(o -> o.sign == sign);
    }

    @Override
    public SymbolType getType() {
        return SymbolType.OPERATOR;
    }
}
