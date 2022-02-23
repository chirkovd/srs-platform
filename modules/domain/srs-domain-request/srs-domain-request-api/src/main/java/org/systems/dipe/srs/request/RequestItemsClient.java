package org.systems.dipe.srs.request;

import java.util.Collection;

public interface RequestItemsClient {

    void create(Collection<RequestItem> items);

    void update(Collection<RequestItem> items);

    Collection<RequestItem> search(RequestItemsSearch search);

    void approve(String itemId);

    void dismiss(String itemId);
}
