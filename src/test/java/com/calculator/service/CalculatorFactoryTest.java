package com.calculator.service;

import com.calculator.model.NotationType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

class CalculatorFactoryTest {

    private static final CalculatorFactory factory = new CalculatorFactory();

    @Test
    void givenInfixNotationWhenGetCalculatorThenReturnsInfixCalculator() {

        assertThat(factory.getCalculator(NotationType.INFIX), instanceOf(InfixCalculator.class));
    }

    @Test
    void givenPrefixNotationWhenGetCalculatorThenReturnsPrefixCalculator() {

        assertThat(factory.getCalculator(NotationType.PREFIX), instanceOf(PrefixCalculator.class));
    }
}