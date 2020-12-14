package com.calculator.model;

import java.util.Objects;

public class SimpleOperand implements Operand {

    private final Integer value;

    public SimpleOperand(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleOperand that = (SimpleOperand) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
