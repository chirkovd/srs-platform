package org.systems.dipe.srs.orchestration.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CamundaFlowsConfig {

    @ComponentScan(basePackages = "org.systems.dipe.srs.orchestration")
    public static class Module {

    }

}
