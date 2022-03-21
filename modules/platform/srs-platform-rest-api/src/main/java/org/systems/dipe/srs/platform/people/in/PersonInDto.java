package org.systems.dipe.srs.platform.people.in;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class PersonInDto {

    //TODO add validation annotations

    private String personId;
    private String firstName;
    private String lastName;
    private ZonedDateTime created;

    private Set<String> roleIds;
    private List<ContactInDto> contacts;
    private Set<IdentificationInDto> identifications;
}
