package org.systems.dipe.srs.person.storage;

import org.systems.dipe.srs.person.identifications.Identification;
import org.systems.dipe.srs.person.identifications.IdentificationSearch;

import java.util.Collection;

public interface IdentificationRepository {

    void create(Collection<Identification> identifications);

    void update(Collection<Identification> identifications);

    Collection<Identification> search(IdentificationSearch search);

}
