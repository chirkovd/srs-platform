package org.systems.dipe.srs.person.identifications;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Identification {

    private String id;
    private String type;
    private String personId;
    private ZonedDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identification that = (Identification) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
