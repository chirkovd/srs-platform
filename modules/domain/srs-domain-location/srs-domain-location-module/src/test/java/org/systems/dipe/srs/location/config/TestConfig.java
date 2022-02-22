package org.systems.dipe.srs.location.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(LocationConfig.Module.class)
@EnableAutoConfiguration
@EnableTransactionManagement
public class TestConfig {

}
