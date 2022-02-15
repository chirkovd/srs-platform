package org.systems.dipe.srs.core;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.systems.dipe.srs.inventory.config.InventoryConfig;
import org.systems.dipe.srs.person.config.PersonConfig;
import org.systems.dipe.srs.request.config.RequestConfig;

@Import({
        InventoryConfig.Module.class,
        PersonConfig.Module.class,
        RequestConfig.Module.class
})
@SpringBootApplication
public class SrsCoreApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder()
                .sources(SrsCoreApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .build()
                .run(args);
    }
}
