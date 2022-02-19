package org.systems.dipe.srs.person.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(PersonConfig.Module.class)
@EnableAutoConfiguration
@EnableTransactionManagement
public class TestConfig {

}
