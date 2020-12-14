package com.calculator.exception;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import io.dropwizard.jersey.errors.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import static com.codahale.metrics.MetricRegistry.name;

public class InvalidExpressionExceptionMapper implements ExceptionMapper<InvalidExpressionException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidExpressionExceptionMapper.class);

    private final Meter exceptions;

    public InvalidExpressionExceptionMapper(MetricRegistry metrics) {
        exceptions = metrics.meter(name(getClass(), "exceptions"));
    }

    @Override
    public Response toResponse(InvalidExpressionException e) {

        LOGGER.info("Invalid expression", e);
        exceptions.mark();
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new ErrorMessage(Response.Status.BAD_REQUEST.getStatusCode(),
                        "You passed an invalid expression!"))
                .build();
    }
}
