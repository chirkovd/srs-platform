package org.systems.dipe.srs.inventory.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InventoryConfig {

    @ComponentScan(basePackages = "org.systems.dipe.srs.inventory")
    public static class Client {

    }
}
