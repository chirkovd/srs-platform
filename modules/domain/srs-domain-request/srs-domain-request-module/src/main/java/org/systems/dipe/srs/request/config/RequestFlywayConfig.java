package org.systems.dipe.srs.request.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class RequestFlywayConfig {

    @Bean(name = "requestFlywayInitializer")
    public FlywayMigrationInitializer flywayMigrationInitializer(DataSource dataSource) {
        return new FlywayMigrationInitializer(
                Flyway.configure()
                        .dataSource(dataSource)
                        .validateOnMigrate(true)
                        .baselineOnMigrate(true)
                        .locations("sql/request/postgresql")
                        .table("schema_version")
                        .schemas("requests")
                        .load()
        );
    }

}
