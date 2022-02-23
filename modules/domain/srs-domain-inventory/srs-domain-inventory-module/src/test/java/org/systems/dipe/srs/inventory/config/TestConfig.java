package org.systems.dipe.srs.inventory.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(InventoryConfig.Module.class)
@EnableAutoConfiguration
@EnableTransactionManagement
public class TestConfig {
}
