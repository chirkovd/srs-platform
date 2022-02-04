package org.systems.dipe.srs.core;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
