package org.systems.dipe.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.systems.dipe.SrsCounterClient;
import org.systems.dipe.SrsMultipleClient;
import org.systems.dipe.SrsSingleClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
public class SrsMetricsBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, String> metrics = new HashMap<>();
    private final ApplicationContext context;
    private final MeterRegistry meterRegistry;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        SrsMetric metric = context.findAnnotationOnBean(beanName, SrsMetric.class);
        if (Objects.isNull(metric)) {
            return bean;
        }
        if (metrics.containsKey(metric.value())) {
            throw new IllegalArgumentException("SRS metric is hold by several beans: "
                    + metrics.get(metric.value()) + " and " + beanName);
        }
        metrics.put(metric.value(), beanName);

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        SrsMetric metric = context.findAnnotationOnBean(beanName, SrsMetric.class);
        if (Objects.isNull(metric)) {
            return bean;
        }
        Counter counter = meterRegistry.counter("srs_platform", "records", metric.value());
        if (bean instanceof SrsSingleClient) {
            return new SrsMetricSingleProxy((SrsSingleClient<Object>) bean, counter);
        }
        if (bean instanceof SrsMultipleClient) {
            return new SrsMetricMultipleProxy((SrsMultipleClient<Object>) bean, counter);
        }
        return bean;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initMetrics() {
        metrics.forEach((metric, beanName) -> {

            Counter counter = Counter.builder("srs_platform")
                    .description("SRS Major Records Count")
                    .baseUnit("records")
                    .tags("record", metric)
                    .register(meterRegistry);

            SrsCounterClient metricHolder = context.getBean(beanName, SrsCounterClient.class);
            counter.increment(metricHolder.count());
        });
    }
}
