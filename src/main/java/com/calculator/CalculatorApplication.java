package com.calculator;

import com.calculator.exception.InvalidExpressionExceptionMapper;
import com.calculator.healthcheck.CalculatorHealthCheck;
import com.calculator.resources.CalculationsResource;
import com.calculator.service.CalculatorFactory;
import com.calculator.service.CalculatorService;
import com.calculator.service.ExpressionParser;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.List.of;

public class CalculatorApplication extends Application<CalculatorConfiguration> {

    @Override
    public String getName() {
        return "Calculator";
    }

    public static void main(String[] args) throws Exception {
        new CalculatorApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CalculatorConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/dist/", "/"));
    }

    @Override
    public void run(CalculatorConfiguration config, Environment env) {

        OpenAPI oas = new OpenAPI();
        Info info = new Info()
                .title("Calculator App")
                .description("This is a calculator app that accepts numerical calculations in prefix and infix notation.")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact()
                        .email("elena.barliba@gmail.com"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

        oas.info(info);
        oas.servers(of(new Server().url("/api")));
        SwaggerConfiguration oasConfig = new SwaggerConfiguration()
                .openAPI(oas)
                .prettyPrint(true)
                .resourcePackages(Stream.of("com.calculator.resources").collect(Collectors.toSet()));

        CalculatorService calculatorService = new CalculatorService(new ExpressionParser(), new CalculatorFactory());
        env.jersey().register(new CalculationsResource(calculatorService));

        env.jersey().register(new OpenApiResource().openApiConfiguration(oasConfig));

        env.jersey().register(new InvalidExpressionExceptionMapper());

        env.healthChecks().register("template",
                new CalculatorHealthCheck());
    }
}