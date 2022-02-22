package org.systems.dipe.srs.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtils {

    private static final ZoneId DEFAULT_ZONE = ZoneId.of("UTC");

    public static LocalDateTime fromZdt(ZonedDateTime zdt) {
        return Optional.ofNullable(zdt).map(ZonedDateTime::toLocalDateTime).orElse(null);
    }

    public static ZonedDateTime now() {
        return ZonedDateTime.now(DEFAULT_ZONE);
    }

    public static ZonedDateTime toZdt(LocalDateTime ldt) {
        return Optional.ofNullable(ldt).map(d -> d.atZone(DEFAULT_ZONE)).orElse(null);
    }

}
