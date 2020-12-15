package com.calculator.model;

/**
 * Represents an operand in an operation; can be a simple operand (an Integer), or an expression
 * (which can be evaluated to an integer).
 */
public interface Operand extends Symbol {

    Integer getValue();

    default SymbolType getType() {
        return SymbolType.OPERAND;
    }
}
