package org.systems.dipe.srs.location.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocationConfig {

    @Import(LocationFlywayConfig.class)
    @ComponentScan(basePackages = "org.systems.dipe.srs.location")
    public static class Module {

    }

}
