package org.systems.dipe.srs.inventory.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InventoryConfig {

    @Import(InventoryFlywayConfig.class)
    @ComponentScan(basePackages = "org.systems.dipe.srs.inventory")
    public static class Module {

    }
}
