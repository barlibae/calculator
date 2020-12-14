package com.calculator.service;

import com.calculator.model.NotationType;
import com.calculator.model.Symbol;

import java.util.List;

public class CalculatorService {

    private final ExpressionParser parser;
    private final CalculatorFactory calculatorFactory;

    public CalculatorService(ExpressionParser parser, CalculatorFactory calculatorFactory) {

        this.parser = parser;
        this.calculatorFactory = calculatorFactory;
    }

    public Integer calculate(String expression) {

        List<Symbol> symbols = parser.parse(expression);
        Calculator calculator = calculatorFactory.getCalculator(NotationType.getType(symbols));

        return calculator.calculate(symbols);
    }
}
