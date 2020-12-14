package com.calculator.resources;

import com.calculator.model.Result;
import com.codahale.metrics.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/calculations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalculationsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculationsResource.class);

    @GET
    @Operation(
            summary = "Evaluates an expression in infix or prefix notation",
            responses = {
                    @ApiResponse(description = "The result of the calculation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class))),
                    @ApiResponse(responseCode = "400", description = "The expression is invalid")})
    @Timed
    public Response evaluate(@NotBlank @QueryParam("expression") String expression) {

        return Response.ok()
                .entity(1.0)
                .build();
    }
}
