package org.systems.dipe.srs.orchestration.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.external.SquadsFacade;
import org.systems.dipe.srs.squad.Squad;
import org.systems.dipe.srs.squad.SquadsClient;

@Component
@AllArgsConstructor
@ConditionalOnBean(SquadsClient.class)
public class SquadsFacadeImpl implements SquadsFacade {

    private final SquadsClient squadsClient;

    @Override
    public Squad create(Squad squad) {
        return squadsClient.create(squad);
    }
}
