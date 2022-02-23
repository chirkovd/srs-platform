package org.systems.dipe.srs.request;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.request.storage.RequestItemsRepository;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class RequestItemsClientImpl implements RequestItemsClient {

    private final RequestItemsRepository repository;

    @Override
    public void create(Collection<RequestItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            return;
        }
        prepareItems(items);
        repository.create(items);
    }

    @Override
    public void update(Collection<RequestItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            return;
        }
        prepareItems(items);
        repository.update(items);
    }

    @Override
    public Collection<RequestItem> search(RequestItemsSearch search) {
        if (CollectionUtils.isEmpty(search.getRequestIds())
                && CollectionUtils.isEmpty(search.getRequestItemIds())) {
            return Collections.emptyList();
        }
        return repository.search(search);
    }

    @Override
    public void approve(String itemId) {
        repository.approve(itemId);
    }

    @Override
    public void dismiss(String itemId) {
        repository.dismiss(itemId);
    }

    private static void prepareItems(Collection<RequestItem> items) {
        for (RequestItem item : items) {
            if (Objects.isNull(item.getItemId())) {
                item.setItemId(UuidUtils.newStr());
            }
            if (Objects.isNull(item.getCreated())) {
                item.setCreated(TimeUtils.now());
            }
        }
    }
}
