package com.calculator.service;

import com.calculator.model.Symbol;

import java.util.List;

public interface Calculator {

    Integer calculate(List<Symbol> expression);
}
