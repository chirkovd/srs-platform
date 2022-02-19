package org.systems.dipe.srs.location.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class LocationFlywayConfig {

    @Bean(name = "locationFlywayInitializer")
    public FlywayMigrationInitializer flywayMigrationInitializer(DataSource dataSource) {
        return new FlywayMigrationInitializer(
                Flyway.configure()
                        .dataSource(dataSource)
                        .validateOnMigrate(true)
                        .baselineOnMigrate(true)
                        .locations("sql/location/postgresql")
                        .table("schema_version")
                        .schemas("locations")
                        .load()
        );
    }

}
