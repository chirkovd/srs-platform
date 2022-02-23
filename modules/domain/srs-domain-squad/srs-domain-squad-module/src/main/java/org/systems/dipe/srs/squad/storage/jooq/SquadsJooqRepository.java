package org.systems.dipe.srs.squad.storage.jooq;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.squad.Squad;
import org.systems.dipe.srs.squad.SquadsSearch;
import org.systems.dipe.srs.squad.jooq.tables.JSquad;
import org.systems.dipe.srs.squad.storage.SquadsRepository;
import org.systems.dipe.srs.squad.storage.mapper.SquadsMapper;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class SquadsJooqRepository implements SquadsRepository {

    private final DefaultDSLContext dsl;
    private final SquadsMapper mapper;

    @Override
    public void create(Squad squad) {
        dsl.insertInto(JSquad.SQUAD)
                .set(mapper.toJooq(squad))
                .execute();
    }

    @Override
    public void update(Squad squad) {
        dsl.update(JSquad.SQUAD)
                .set(mapper.toJooq(squad))
                .where(JSquad.SQUAD.SQUAD_ID.eq(UuidUtils.fromStr(squad.getSquadId())))
                .execute();
    }

    @Override
    public Collection<Squad> search(SquadsSearch search) {
        return dsl.selectFrom(JSquad.SQUAD)
                .where(JSquad.SQUAD.SQUAD_ID.in(UuidUtils.fromStr(search.getSquadIds())))
                .fetch()
                .map(mapper::fromJooq);
    }
}
