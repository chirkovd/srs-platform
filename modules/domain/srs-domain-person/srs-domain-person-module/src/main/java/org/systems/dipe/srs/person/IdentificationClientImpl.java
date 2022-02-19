package org.systems.dipe.srs.person;

import org.springframework.stereotype.Service;
import org.systems.dipe.srs.person.identifications.Identification;
import org.systems.dipe.srs.person.identifications.IdentificationClient;
import org.systems.dipe.srs.person.identifications.IdentificationSearch;

import java.util.Collection;

@Service
public class IdentificationClientImpl implements IdentificationClient {

    @Override
    public void create(Collection<Identification> identifications) {

    }

    @Override
    public void update(Collection<Identification> identifications) {

    }

    @Override
    public Collection<Identification> search(IdentificationSearch search) {
        return null;
    }
}
