package org.systems.dipe.srs.platform.people.out;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class PersonOutDto {

    private String personId;
    private String firstName;
    private String lastName;
    private ZonedDateTime created;

    private Collection<RoleOutDto> roles;
    private Collection<ContactOutDto> contacts;
    private Collection<IdentificationOutDto> identifications;
}
