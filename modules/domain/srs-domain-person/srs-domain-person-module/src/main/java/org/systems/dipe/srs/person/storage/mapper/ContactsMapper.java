package org.systems.dipe.srs.person.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.jooq.tables.records.JContactRecord;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ContactsMapper extends CommonMapper {

    JContactRecord toJooq(Contact contact);

    Contact fromJooq(JContactRecord record);

}
