package org.systems.dipe.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SrsMetricProxy {

    private final MeterRegistry registry;
    private final Object bean;



}
