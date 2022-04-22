package org.systems.dipe.metrics;

import io.micrometer.core.instrument.Counter;
import lombok.AllArgsConstructor;
import org.systems.dipe.SrsSingleClient;

@AllArgsConstructor
public class SrsMetricSingleProxy implements SrsSingleClient<Object> {

    private final SrsSingleClient<Object> delegate;
    private final Counter counter;

    @Override
    public double count() {
        return delegate.count();
    }

    @Override
    public Object create(Object item) {
        Object result = delegate.create(item);
        counter.increment();
        return result;
    }
}
