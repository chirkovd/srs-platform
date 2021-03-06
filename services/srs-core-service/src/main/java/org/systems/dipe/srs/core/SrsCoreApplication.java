package org.systems.dipe.srs.core;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.systems.dipe.srs.inventory.config.InventoryConfig;
import org.systems.dipe.srs.location.config.LocationConfig;
import org.systems.dipe.srs.orchestration.config.CamundaConfig;
import org.systems.dipe.srs.person.config.PersonConfig;
import org.systems.dipe.srs.platform.config.PlatformRestConfig;
import org.systems.dipe.srs.request.config.RequestConfig;
import org.systems.dipe.srs.search.config.SearchProcessConfig;
import org.systems.dipe.srs.squad.config.SquadConfig;

@Import({
        InventoryConfig.Module.class,
        PersonConfig.Module.class,
        RequestConfig.Module.class,
        LocationConfig.Module.class,
        SquadConfig.Module.class,
        SearchProcessConfig.Module.class,
        CamundaConfig.Module.class,
        PlatformRestConfig.Module.class
})
@SpringBootApplication
@EnableTransactionManagement
public class SrsCoreApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder()
                .sources(SrsCoreApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .build()
                .run(args);
    }
}
