package org.systems.dipe.srs.person;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.person.identifications.Identification;
import org.systems.dipe.srs.person.identifications.IdentificationClient;
import org.systems.dipe.srs.person.identifications.IdentificationSearch;
import org.systems.dipe.srs.person.storage.IdentificationRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class IdentificationClientImpl implements IdentificationClient {

    private final IdentificationRepository repository;

    @Override
    public void create(Collection<Identification> identifications) {
        if (CollectionUtils.isEmpty(identifications)) {
            return;
        }
        for (Identification identification : identifications) {
            if (Objects.isNull(identification.getCreated())) {
                identification.setCreated(ZonedDateTime.now(ZoneId.of("UTC")));
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
    public Collection<Identification> search(IdentificationSearch search) {
        if (CollectionUtils.isEmpty(search.getPersonIds())
                && CollectionUtils.isEmpty(search.getTypes())
                && CollectionUtils.isEmpty(search.getValues())) {
            return Collections.emptyList();
        }
        return repository.search(search);
    }
}
