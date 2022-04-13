package org.systems.dipe.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SrsMetricsConfiguration {

    @Bean
    public BeanPostProcessor srsMetricsPostProcessor(ApplicationContext context, MeterRegistry registry) {
        return new SrsMetricsBeanPostProcessor(context, registry);
    }

}
