package org.systems.dipe.srs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class RequestItemsSearch {
    private final Set<String> requestIds;
    private final Set<String> requestItemIds;
}
