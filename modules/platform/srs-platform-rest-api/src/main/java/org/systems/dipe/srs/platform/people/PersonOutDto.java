package org.systems.dipe.srs.platform.people;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class PersonOutDto {

    //TODO add validation annotations

    private String personId;
    private String firstName;
    private String lastName;
    private ZonedDateTime created;

    private Collection<RoleDto> roles;
    private Collection<ContactDto> contacts;
    private Collection<IdentificationDto> identifications;
}
