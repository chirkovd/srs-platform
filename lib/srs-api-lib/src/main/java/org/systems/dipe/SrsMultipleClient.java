package org.systems.dipe;

import java.util.Collection;

public interface SrsMultipleClient<T> extends SrsCounterClient {

    void create(Collection<T> items);

}
