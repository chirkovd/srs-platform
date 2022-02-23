package org.systems.dipe.srs.request.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(RequestConfig.Module.class)
@EnableAutoConfiguration
@EnableTransactionManagement
public class TestConfig {
}
