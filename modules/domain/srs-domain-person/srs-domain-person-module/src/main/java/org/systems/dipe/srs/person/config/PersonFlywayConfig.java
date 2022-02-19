package org.systems.dipe.srs.person.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class PersonFlywayConfig {

    @Bean(name = "personFlywayInitializer")
    public FlywayMigrationInitializer flywayMigrationInitializer(DataSource dataSource) {
        return new FlywayMigrationInitializer(
                Flyway.configure()
                        .dataSource(dataSource)
                        .validateOnMigrate(true)
                        .baselineOnMigrate(true)
                        .locations("sql/person/postgresql")
                        .table("schema_version")
                        .schemas("srs_person")
                        .load()
        );
    }

}
