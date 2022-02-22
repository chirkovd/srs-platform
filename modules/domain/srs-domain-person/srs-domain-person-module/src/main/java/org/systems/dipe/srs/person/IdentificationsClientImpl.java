package org.systems.dipe.srs.person;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.person.identifications.Identification;
import org.systems.dipe.srs.person.identifications.IdentificationsClient;
import org.systems.dipe.srs.person.identifications.IdentificationsSearch;
import org.systems.dipe.srs.person.storage.IdentificationsRepository;
import org.systems.dipe.srs.utils.TimeUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class IdentificationsClientImpl implements IdentificationsClient {

    private final IdentificationsRepository repository;

    @Override
    public void create(Collection<Identification> identifications) {
        if (CollectionUtils.isEmpty(identifications)) {
            return;
        }
        for (Identification identification : identifications) {
            if (Objects.isNull(identification.getCreated())) {
                identification.setCreated(TimeUtils.now());
            }
        }
        repository.create(identifications);
    }

    @Override
    public void update(Collection<Identification> identifications) {
        if (CollectionUtils.isEmpty(identifications)) {
            return;
        }
        repository.update(identifications);
    }

    @Override
    public Collection<Identification> search(IdentificationsSearch search) {
        if (CollectionUtils.isEmpty(search.getPersonIds())
                && CollectionUtils.isEmpty(search.getTypes())
                && CollectionUtils.isEmpty(search.getValues())) {
            return Collections.emptyList();
        }
        return repository.search(search);
    }
}
