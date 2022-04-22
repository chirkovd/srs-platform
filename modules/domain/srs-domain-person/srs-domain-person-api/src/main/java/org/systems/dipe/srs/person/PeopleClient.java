package org.systems.dipe.srs.person;

import org.systems.dipe.SrsSingleClient;

import java.util.Collection;

public interface PeopleClient extends SrsSingleClient<Person> {

    Person update(Person person);

    Collection<Person> find(PeopleSearch search);
}
