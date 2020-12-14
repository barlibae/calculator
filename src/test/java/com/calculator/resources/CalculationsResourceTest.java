package com.calculator.resources;

import com.calculator.model.Result;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.reset;


@ExtendWith(DropwizardExtensionsSupport.class)
class CalculationsResourceTest {

    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new CalculationsResource())
            .build();

    @BeforeEach
    void setup() {

    }

    @AfterEach
    void tearDown() {
        reset();
    }

    @Test
    void getPersonSuccess() {

        String expression = "- 12 * 2 6";

        EXT.target("/calculations")
                .queryParam("expression", expression)
                .request()
                .get(Result.class);
    }
}