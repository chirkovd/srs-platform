package org.systems.dipe.srs.orchestration.external;

import org.systems.dipe.srs.squad.Squad;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

public class SquadsFacadeTestImpl implements SquadsFacade {
    @Override
    public Squad create(Squad squad) {
        squad.setSquadId(UuidUtils.newStr());
        squad.setCreated(TimeUtils.now());
        return squad;
    }
}
