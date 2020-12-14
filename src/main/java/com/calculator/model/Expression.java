package com.calculator.model;

import java.util.Objects;

/**
 * A mathematical expression (representing a binary operation),
 * where the operands can represent expressions themselves.
 */
public class Expression implements Operand {

    private final Operand left;
    private final Operand right;
    private final Operator operator;

    public Expression(Operand left, Operand right, Operator operator) {

        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Integer getValue() {

        return operator.apply(left, right).getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expression that = (Expression) o;
        return left.equals(that.left) && right.equals(that.right) && operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, operator);
    }
}
