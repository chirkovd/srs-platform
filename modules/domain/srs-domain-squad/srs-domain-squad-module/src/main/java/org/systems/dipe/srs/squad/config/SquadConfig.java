package org.systems.dipe.srs.squad.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SquadConfig {

    @ComponentScan(basePackages = "org.systems.dipe.srs.squad")
    public static class Module {

    }

}
