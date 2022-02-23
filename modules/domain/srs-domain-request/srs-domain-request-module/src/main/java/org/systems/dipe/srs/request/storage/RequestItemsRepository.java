package org.systems.dipe.srs.request.storage;

import org.systems.dipe.srs.request.RequestItem;
import org.systems.dipe.srs.request.RequestItemsSearch;

import java.util.Collection;

public interface RequestItemsRepository {

    void create(Collection<RequestItem> items);

    void update(Collection<RequestItem> items);

    Collection<RequestItem> search(RequestItemsSearch search);

    void approve(String itemId);

    void dismiss(String itemId);

}
