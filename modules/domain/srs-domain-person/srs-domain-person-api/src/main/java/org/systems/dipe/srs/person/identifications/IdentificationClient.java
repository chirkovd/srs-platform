package org.systems.dipe.srs.person.identifications;

import java.util.Collection;

public interface IdentificationClient {

    void create(Collection<Identification> identifications);

    void update(Collection<Identification> identifications);

    Collection<Identification> search(IdentificationSearch search);
}
