package com.calculator.healthcheck;

import com.codahale.metrics.health.HealthCheck;

public class CalculatorHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {

        return Result.healthy("OK");
    }
}


