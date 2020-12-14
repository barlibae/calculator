package com.calculator.resources;

import com.calculator.exception.InvalidExpressionException;
import com.calculator.exception.InvalidExpressionExceptionMapper;
import com.calculator.model.Result;
import com.calculator.service.CalculatorService;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(DropwizardExtensionsSupport.class)
class CalculationsResourceTest {

    private static final CalculatorService serviceMock = mock(CalculatorService.class);

    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new CalculationsResource(serviceMock))
            .addResource(new InvalidExpressionExceptionMapper())
            .build();

    @AfterEach
    void tearDown() {
        reset(serviceMock);
    }

    @Test
    void givenExpressionWhenCalculateThenReturnsResult() {

        String expression = "- 12 * 2 6";

        int result = 0;
        Result expected = new Result(result);

        when(serviceMock.calculate(expression)).thenReturn(result);

        Result actual = EXT.target("/calculations")
                .queryParam("expression", expression)
                .request()
                .get(Result.class);

        assertEquals(expected, actual);
    }

    @Test
    void givenExceptionThrownWhenCalculateThenReturnsBadRequest() {

        String expression = "- 12 * 2 6";

        when(serviceMock.calculate(expression)).thenThrow(new InvalidExpressionException("simulated"));

        Response response = EXT.target("/calculations")
                .queryParam("expression", expression)
                .request()
                .get(Response.class);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}