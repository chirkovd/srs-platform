package org.systems.dipe.srs.request.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestConfig {

    @ComponentScan(basePackages = "org.systems.dipe.srs.request")
    public static class Module {

    }

}
