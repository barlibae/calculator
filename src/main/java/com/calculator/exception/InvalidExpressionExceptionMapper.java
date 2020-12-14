package com.calculator.exception;

import io.dropwizard.jersey.errors.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidExpressionExceptionMapper implements ExceptionMapper<InvalidExpressionException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidExpressionExceptionMapper.class);

    @Override
    public Response toResponse(InvalidExpressionException e) {

        LOGGER.info("Invalid expression", e);

        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new ErrorMessage(Response.Status.BAD_REQUEST.getStatusCode(),
                        "You passed an invalid expression!"))
                .build();
    }
}
