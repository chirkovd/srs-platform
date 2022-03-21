package org.systems.dipe.srs.platform.external;

import org.systems.dipe.srs.platform.squad.out.SquadOutDto;

import java.util.List;
import java.util.Set;

public interface SquadFacade {
    List<SquadOutDto> search(Set<String> squadIds);

    void joinSquad(String squadId, String volunteerId);
}
