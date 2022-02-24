package org.systems.dipe.srs.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchProcess {
    private String searchId;
    private String requestId;
    private SearchProcessStatus status;
    private ZonedDateTime created;
    private List<SearchLocation> locations;
    private List<SearchSquad> squads;
}
