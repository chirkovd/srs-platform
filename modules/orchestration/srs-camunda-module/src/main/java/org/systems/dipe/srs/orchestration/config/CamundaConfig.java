package org.systems.dipe.srs.orchestration.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CamundaConfig {

    @EnableProcessApplication
    @PropertySource("camunda.properties")
    @Import(CamundaFlowsConfig.Module.class)
    @ComponentScan(basePackages = "org.systems.dipe.srs.orchestration")
    public static class Module {

    }

}
