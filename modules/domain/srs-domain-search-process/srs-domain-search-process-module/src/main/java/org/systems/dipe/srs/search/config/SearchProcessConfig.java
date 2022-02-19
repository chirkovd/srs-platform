package org.systems.dipe.srs.search.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchProcessConfig {

    @Import(SearchProcessFlywayConfig.class)
    @ComponentScan(basePackages = "org.systems.dipe.srs.search")
    public static class Module {

    }

}
