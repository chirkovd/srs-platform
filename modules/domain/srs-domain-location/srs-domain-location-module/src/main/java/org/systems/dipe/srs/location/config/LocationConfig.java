package org.systems.dipe.srs.location.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocationConfig {

    @ComponentScan(basePackages = "org.systems.dipe.srs.location")
    public static class Module {

    }

}
