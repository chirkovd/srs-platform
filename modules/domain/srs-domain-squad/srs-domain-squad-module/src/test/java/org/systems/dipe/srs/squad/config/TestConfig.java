package org.systems.dipe.srs.squad.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(SquadConfig.Module.class)
@EnableAutoConfiguration
@EnableTransactionManagement
public class TestConfig {
}
