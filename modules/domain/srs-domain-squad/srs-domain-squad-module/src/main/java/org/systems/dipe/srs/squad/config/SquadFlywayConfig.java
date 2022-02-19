package org.systems.dipe.srs.squad.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class SquadFlywayConfig {

    @Bean(name = "squadFlywayInitializer")
    public FlywayMigrationInitializer flywayMigrationInitializer(DataSource dataSource) {
        return new FlywayMigrationInitializer(
                Flyway.configure()
                        .dataSource(dataSource)
                        .validateOnMigrate(true)
                        .baselineOnMigrate(true)
                        .locations("sql/squad/postgresql")
                        .table("schema_version")
                        .schemas("squads")
                        .load()
        );
    }

}
