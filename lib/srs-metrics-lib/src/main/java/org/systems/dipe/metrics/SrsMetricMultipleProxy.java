package org.systems.dipe.metrics;

import io.micrometer.core.instrument.Counter;
import lombok.AllArgsConstructor;
import org.systems.dipe.SrsMultipleClient;

import java.util.Collection;

@AllArgsConstructor
public class SrsMetricMultipleProxy implements SrsMultipleClient<Object> {

    private final SrsMultipleClient<Object> delegate;
    private final Counter counter;

    @Override
    public double count() {
        return delegate.count();
    }

    @Override
    public void create(Collection<Object> items) {
        delegate.create(items);
        counter.increment(items.size());
    }
}
