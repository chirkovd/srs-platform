package org.systems.dipe.srs.orchestration.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.systems.dipe.srs.orchestration.external.RequestsFacade;
import org.systems.dipe.srs.orchestration.external.RequestsFacadeTestImpl;
import org.systems.dipe.srs.orchestration.external.SearchProcessFacade;
import org.systems.dipe.srs.orchestration.external.SearchProcessFacadeTestImpl;

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

}
