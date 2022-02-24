package org.systems.dipe.srs.search.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(SearchProcessConfig.Module.class)
@EnableAutoConfiguration
@EnableTransactionManagement
public class TestConfig {
}
