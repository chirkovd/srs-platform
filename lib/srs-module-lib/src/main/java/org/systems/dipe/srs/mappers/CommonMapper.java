package org.systems.dipe.srs.mappers;

import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

public interface CommonMapper {

    default LocalDateTime fromZdt(ZonedDateTime zdt) {
        return TimeUtils.fromZdt(zdt);
    }

    default ZonedDateTime toZdt(LocalDateTime ldt) {
        return TimeUtils.toZdt(ldt);
    }

    default UUID toStr(String uuid) {
        return UuidUtils.fromStr(uuid);
    }

    default String fromStr(UUID uuid) {
        return UuidUtils.toStr(uuid);
    }

}
