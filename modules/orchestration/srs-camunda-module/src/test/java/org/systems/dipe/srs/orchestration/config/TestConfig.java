package org.systems.dipe.srs.orchestration.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.systems.dipe.srs.orchestration.external.*;

@Import(CamundaConfig.Module.class)
@EnableAutoConfiguration
@EnableTransactionManagement
public class TestConfig {

    @Bean
    public RequestsFacade requestsFacade() {
        return new RequestsFacadeTestImpl();
    }

    @Bean
    public SearchProcessFacade searchProcessFacade() {
        return new SearchProcessFacadeTestImpl();
    }

    @Bean
    public SquadsFacade squadsFacade() {
        return new SquadsFacadeTestImpl();
    }

}
