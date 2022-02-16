package org.systems.dipe.srs.search.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchProcessConfig {

    @ComponentScan(basePackages = "org.systems.dipe.srs.search")
    public static class Module {

    }

}
