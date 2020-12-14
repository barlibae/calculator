package com.calculator.service;

import com.calculator.model.NotationType;

public class CalculatorFactory {

    public Calculator getCalculator(NotationType notationType) {

        if (notationType == NotationType.INFIX) {
            return new InfixCalculator();
        }

        return new PrefixCalculator();
    }
}
