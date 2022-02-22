package org.systems.dipe.srs.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UuidUtils {

    public static String newStr() {
        return UUID.randomUUID().toString();
    }

    public static UUID fromStr(String uuid) {
        return Optional.ofNullable(uuid).map(UUID::fromString).orElse(null);
    }

    public static Collection<UUID> fromStr(Collection<String> uuids) {
        if (CollectionUtils.isEmpty(uuids)) {
            return Collections.emptySet();
        }
        return uuids.stream().map(UuidUtils::fromStr).collect(Collectors.toSet());
    }

    public static String toStr(UUID uuid) {
        return Optional.ofNullable(uuid).map(UUID::toString).orElse(null);
    }
}
