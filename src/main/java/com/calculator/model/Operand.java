package com.calculator.model;

public interface Operand extends Symbol {

    Integer getValue();

    default SymbolType getType() {
        return SymbolType.OPERAND;
    }
}
