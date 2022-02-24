package org.systems.dipe.srs.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocation {
    private String locationId;
    private String searchId;
    private ZonedDateTime created;
}
