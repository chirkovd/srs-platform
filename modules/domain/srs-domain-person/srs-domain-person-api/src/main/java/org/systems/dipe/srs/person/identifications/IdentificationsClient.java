package org.systems.dipe.srs.person.identifications;

import java.util.Collection;

public interface IdentificationsClient {

    void create(Collection<Identification> identifications);

    void update(Collection<Identification> identifications);

    Collection<Identification> search(IdentificationsSearch search);
}
