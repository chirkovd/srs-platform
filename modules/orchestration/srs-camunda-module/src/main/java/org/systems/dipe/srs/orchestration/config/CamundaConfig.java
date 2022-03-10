package org.systems.dipe.srs.orchestration.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.systems.dipe.srs.orchestration.events.EventType;
import org.systems.dipe.srs.orchestration.events.EventTypeDeserializer;
import org.systems.dipe.srs.orchestration.events.EventTypeProvider;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CamundaConfig {

    @EnableProcessApplication
    @PropertySource("classpath:camunda.properties")
    @Import({
            CamundaFlowsConfig.Module.class,
            CamundaFlywayConfig.class,
            SrsExchangeConfig.class
    })
    @ComponentScan(basePackages = "org.systems.dipe.srs.orchestration")
    public static class Module {

        @Bean
        public SimpleModule eventTypeDeserializerModule(EventTypeProvider eventTypeProvider) {
            SimpleModule module = new SimpleModule();
            module.addDeserializer(EventType.class, new EventTypeDeserializer(eventTypeProvider));
            return module;
        }

    }

}
