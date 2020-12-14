package com.calculator.model;

/**
 * Represents a mathematical symbol (can be an Integer, an operator, e.g. +, -; parenthesis etc
 * or even a mathematical expression).
 */
public interface Symbol {

    SymbolType getType();
}
