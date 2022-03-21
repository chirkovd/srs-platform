package org.systems.dipe.srs.platform.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlatformRestConfig {

    @ComponentScan(basePackages = "org.systems.dipe.srs.platform")
    public static class Module {

    }

}
