package org.systems.dipe.srs.search.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class SearchProcessFlywayConfig {

    @Bean(name = "searchProcessFlywayInitializer")
    public FlywayMigrationInitializer flywayMigrationInitializer(DataSource dataSource) {
        return new FlywayMigrationInitializer(
                Flyway.configure()
                        .dataSource(dataSource)
                        .validateOnMigrate(true)
                        .baselineOnMigrate(true)
                        .locations("sql/search/postgresql")
                        .table("schema_version")
                        .schemas("searches")
                        .load()
        );
    }

}
