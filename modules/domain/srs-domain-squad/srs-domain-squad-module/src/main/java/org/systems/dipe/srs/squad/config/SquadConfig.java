package org.systems.dipe.srs.squad.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SquadConfig {

    @Import(SquadFlywayConfig.class)
    @ComponentScan(basePackages = "org.systems.dipe.srs.squad")
    public static class Module {

    }

}
