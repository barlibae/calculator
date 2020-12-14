package com.calculator.service;

import com.calculator.model.SimpleOperand;
import com.calculator.model.Symbol;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.calculator.model.NotationType.PREFIX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {

    @Mock
    private ExpressionParser parserMock;
    @Mock
    private CalculatorFactory factoryMock;
    @Mock
    private Calculator calculatorMock;

    @InjectMocks
    private CalculatorService service;

    @Test
    void givenExpressionWhenCalculateThenDelegatesToExpectedCalculator() {

        String expression = "2";
        int expectedResult = 2;
        List<Symbol> symbols = List.of(new SimpleOperand(expectedResult));

        when(parserMock.parse(expression)).thenReturn(symbols);
        when(factoryMock.getCalculator(PREFIX)).thenReturn(calculatorMock);
        when(calculatorMock.calculate(symbols)).thenReturn(expectedResult);

        assertEquals(expectedResult, service.calculate(expression));
    }
}