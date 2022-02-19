package org.systems.dipe.srs.request.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestConfig {

    @Import(RequestFlywayConfig.class)
    @ComponentScan(basePackages = "org.systems.dipe.srs.request")
    public static class Module {

    }

}
