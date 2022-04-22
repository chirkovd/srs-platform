package org.systems.dipe;

public interface SrsSingleClient<T> extends SrsCounterClient {

    T create(T item);
}
